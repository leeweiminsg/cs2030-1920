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
   */
  public EventHandler(int seed, int numOfServers, int queueCapacity, int numOfCustomers, double arrivalRate, double serviceRate, double restingRate, double restingProbability) {
    eventQueue = new PriorityQueue<>();
    serverList = new ArrayList<>();
    rng = new RandomGenerator(seed, arrivalRate, serviceRate, restingRate);
    stats = new Statistics();
    createServers(numOfServers, queueCapacity, restingProbability);
    createArrivalEvents(numOfCustomers);
  }

  /**
   * Create servers.
   * 
   * @param n number of servers
   */
  void createServers(int n, int queueCapacity, double restingProbability) {
    for (int i = 0; i < n; i++) {
      serverList.add(new Server(i + 1, queueCapacity, restingProbability, rng));
    }
  }

  /**
   * Create arrival events.
   * 
   * @param n number of customers
   */
  void createArrivalEvents(int n) {
    double arrivalTime = 0;

    for (int i = 0; i < n; i++) {
      if (i != 0) {
        arrivalTime += rng.genInterArrivalTime();
      }
      
      Customer customer = new Customer(arrivalTime);
      Event event = new Event(customer);
      eventQueue.add(event);
    }
  }

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
      for (Server server : serverList) {
        if (!server.isFull()) {
          Event waitEvent = arriveEvent.wait(server);
          eventQueue.add(waitEvent);

          willBeServed = true;
          break;
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

  void processServedEvent(Event servedEvent) {
    System.out.println(servedEvent);

    stats.addTotalWaitingTime(servedEvent.getCustomer().getWaitingTime());

    Event doneEvent = servedEvent.done();
    eventQueue.add(doneEvent);
  }
  
  void processLeaveEvent(Event leaveEvent) {
    System.out.println(leaveEvent);
  }

  void processDoneEvent(Event doneEvent) {
    System.out.println(doneEvent);

    Event newEvent = doneEvent.processDone();

    if (newEvent != null) {
      eventQueue.add(newEvent);
    }
  }

  void processWaitEvent(Event waitEvent) {
    System.out.println(waitEvent);

    waitEvent.processWait();
  }

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

  public void displayStats() {
    stats.displayStats();
  }
}