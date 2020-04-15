package cs2030.simulator;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class EventHandler {
    private PriorityQueue<Event> eventQueue;
    private ArrayList<Server> serverList;
    private RandomGenerator rng;
    private Statistics stats;

    /**
     * Constructor.
     * 
     * @param seed                      seed
     * @param numOfHumanServers         number of human servers
     * @param numOfSelfCheckoutCounters number of self checkout counters
     * @param queueCapacity             queue capacity
     * @param numOfCustomers            number of customers
     * @param arrivalRate               arrival rate
     * @param serviceRate               service rate
     * @param restingRate               resting rate
     * @param restingProbability        resting probability
     * @param greedyCustomerProbability greedy customer probability
     */
    public EventHandler(int seed, int numOfHumanServers,
            int numOfSelfCheckoutCounters, 
            int queueCapacity, int numOfCustomers,
            double arrivalRate, 
            double serviceRate, double restingRate, 
            double restingProbability, double greedyCustomerProbability) {
        eventQueue = new PriorityQueue<>();
        serverList = new ArrayList<>();
        rng = new RandomGenerator(seed, arrivalRate, serviceRate, restingRate);
        stats = new Statistics();
        createServers(numOfHumanServers, queueCapacity, 
            restingProbability, numOfSelfCheckoutCounters);
        createArrivalEvents(numOfCustomers, greedyCustomerProbability);
    }

    /**
     * Create servers.
     * 
     * @param numOfHumanServers         number of human servers
     * @param queueCapacity             queue capacity
     * @param restingProbability        resting probability
     * @param numOfSelfCheckoutCounters number of self checkout counters
     */
    void createServers(int numOfHumanServers, int queueCapacity, 
            double restingProbability, int numOfSelfCheckoutCounters) {
        for (int i = 0; i < numOfHumanServers; i++) {
            serverList.add(new HumanServer(i + 1, queueCapacity, restingProbability, rng));
        }

        for (int j = 0; j < numOfSelfCheckoutCounters; j++) {
            serverList.add(new SelfCheckoutCounter(j + numOfHumanServers, queueCapacity, rng));
        }
    }

    /**
     * Create arrival events.
     * 
     * @param numOfCustomers            number of customers
     * @param greedyCustomerProbability greedy customer probability
     */
    void createArrivalEvents(int numOfCustomers, double greedyCustomerProbability) {
        double arrivalTime = 0;

        for (int i = 0; i < numOfCustomers; i++) {
            if (i != 0) {
                arrivalTime += rng.genInterArrivalTime();
            }

            Customer customer = Customer.canCreateGreedyCustomer(rng, greedyCustomerProbability)
                    ? Customer.createGreedyCustomer(arrivalTime)
                    : Customer.createNormalCustomer(arrivalTime);
            Event event = new Event(customer);
            eventQueue.add(event);
        }
    }

    /**
     * Process events.
     */
    public void processEvents() {
        while (!eventQueue.isEmpty()) {
            Event event = eventQueue.poll();

            if (event.isArriveEvent()) {
                processArriveEvent(event);
            } else if (event.isServedEvent()) {
                processServedEvent(event);
            } else if (event.isWaitEvent()) {
                processWaitEvent(event);
            } else if (event.isLeaveEvent()) {
                processLeaveEvent(event);
            } else if (event.isDoneEvent()) {
                processDoneEvent(event);
            } else if (event.isServerEvent()) {
                processServerEvent(event);
            }
        }
    }

    /**
     * Process arrival event.
     * 
     * @param Event arrival event
     */
    void processArriveEvent(Event arriveEvent) {
        boolean willBeServed = false;
        System.out.println(arriveEvent);

        for (Server server : serverList) {
            if (server.isIdle()) {
                Event servedEvent = arriveEvent.serve(server);
                eventQueue.add(servedEvent);

                willBeServed = true;
                break;
            }
        }

        if (!willBeServed) {
            if (arriveEvent.getCustomer().isGreedy()) {
                Server minQueueServer = null;

                for (Server server : serverList) {
                    if (!server.isFull()
                            && (minQueueServer == null 
                                || server.getQueueLength() < minQueueServer.getQueueLength())) {
                        minQueueServer = server;
                    }
                }

                if (minQueueServer != null) {
                    Event waitEvent = arriveEvent.wait(minQueueServer);
                    eventQueue.add(waitEvent);

                    willBeServed = true;
                }
            } else {
                for (Server server : serverList) {
                    if (!server.isFull()) {
                        Event waitEvent = arriveEvent.wait(server);
                        eventQueue.add(waitEvent);

                        willBeServed = true;
                        break;
                    }
                }
            }
        }

        if (willBeServed) {
            stats.addServedCustomersByOne();
        } else {
            stats.addLeftCustomersByOne();

            Event leftEvent = arriveEvent.leave();
            eventQueue.add(leftEvent);
        }
    }

    /**
     * Process serve event.
     * 
     * @param Event serve event
     */
    void processServedEvent(Event servedEvent) {
        System.out.println(servedEvent);

        stats.addTotalWaitingTime(servedEvent.getCustomer().getWaitingTime());

        Event doneEvent = servedEvent.done();
        eventQueue.add(doneEvent);
    }

    /**
     * Process leave event.
     * 
     * @param Event leave event
     */
    void processLeaveEvent(Event leaveEvent) {
        System.out.println(leaveEvent);
    }

    /**
     * Process done event.
     * 
     * @param Event done event
     */
    void processDoneEvent(Event doneEvent) {
        System.out.println(doneEvent);

        Event newEvent = doneEvent.processDone();

        if (newEvent != null) {
            eventQueue.add(newEvent);
        }
    }

    /**
     * Process wait event.
     * 
     * @param Event wait event
     */
    void processWaitEvent(Event waitEvent) {
        System.out.println(waitEvent);

        waitEvent.processWait();
    }

    /**
     * Process server event.
     * 
     * @param Event server event
     */
    void processServerEvent(Event serverEvent) {
        if (serverEvent.isServerRestEvent()) {
            Event serverBackUpEvent = serverEvent.serverBack();

            eventQueue.add(serverBackUpEvent);
        } else {
            Event newEvent = serverEvent.processServerBackUpEvent();

            if (newEvent != null) {
                eventQueue.add(newEvent);
            }
        }
    }

    /**
     * Display statistics.
     */
    public void displayStats() {
        stats.displayStats();
    }
}
