package cs2030.simulator;

public class Event implements Comparable<Event> {
    private final Customer customer;
    private final Server server;

    private enum EventState {
        SERVER_REST, SERVER_BACK, ARRIVES, SERVED, LEAVES, WAITS, DONE
    }

    private final EventState eventStatus;
    private final double time;

    /**
     * Constructor.
     * 
     * @param customer customer
     */
    Event(Customer customer) {
        this.customer = customer;
        server = null;
        eventStatus = EventState.ARRIVES;
        time = customer.getArrivalTime();
    }

    /**
     * Overloaded constructor.
     * 
     * @param customer    customer
     * @param server      server
     * @param eventStatus event status
     * @param time        time
     */
    Event(Customer customer, Server server, EventState eventStatus, double time) {
        this.customer = customer;
        this.server = server;
        this.eventStatus = eventStatus;
        this.time = time;
    }

    /**
     * Creates new serve event from arrive event.
     * 
     * @param Event arriveEvent
     * @return serve event
     */
    Event serve(Server server) {
        return new Event(customer.setServed(), server, EventState.SERVED, time);
    }

    /**
     * Creates new wait event from arrive event.
     * 
     * @param server server
     * @return wait event
     */
    Event wait(Server server) {
        return new Event(customer.setWait(), server, EventState.WAITS, time);
    }

    /**
     * Creates new leave event from arrive event.
     * 
     * @return leave event
     */
    Event leave() {
        return new Event(customer.setLeave(), null, EventState.LEAVES, time);
    }

    /**
     * Creates new done event from serve event.
     * 
     * @return done event
     */
    Event done() {
        Customer doneCustomer = server.serve(customer);
        return new Event(doneCustomer, server, EventState.DONE, doneCustomer.getTime());
    }

    /**
     * Creates new server rest event from done event.
     * 
     * @return server rest event
     */
    Event serverRest() {
        return new Event(null, server, EventState.SERVER_REST, time);
    }

    /**
     * Creates new server back up event from server rest event.
     * 
     * @return server back event
     */
    Event serverBack() {
        return new Event(null, server, EventState.SERVER_BACK, server.getNextServeTime());
    }

    /**
     * Processes wait event.
     * 
     */
    void processWait() {
        server.addToQueue(customer);
    }

    /**
     * Processes and creates new event from done event (if there are waiting
     * customers or server will rest).
     * 
     * @return serve event or null
     */
    Event processDone() {
        Customer newServedCustomer = server.done();

        if (newServedCustomer == null) {
            return server.isResting() ? serverRest() : null;
        }

        return new Event(newServedCustomer, server, EventState.SERVED, newServedCustomer.getTime());
    }

    /**
     * Processes and creates new serve event from server back up event (if there are
     * waiting customers).
     * 
     * @return serve event or null
     */
    Event processServerBackUpEvent() {
        Customer newServedCustomer = server.back();

        if (newServedCustomer == null) {
            return null;
        }

        return new Event(newServedCustomer, server, EventState.SERVED, newServedCustomer.getTime());
    }

    /**
     * Get customer.
     * 
     * @return customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Get server.
     * 
     * @return server
     */
    public Server getServer() {
        return server;
    }

    /**
     * Get event time.
     * 
     * @return time
     */
    public double getTime() {
        return time;
    }

    /**
     * Check whether event is a server event.
     * 
     * @return boolean
     */
    public boolean isServerEvent() {
        return isServerRestEvent() || isServerBackEvent();
    }

    /**
     * Check whether event is a server rest event.
     * 
     * @return boolean
     */
    public boolean isServerRestEvent() {
        return eventStatus == EventState.SERVER_REST;
    }

    /**
     * Check whether event is a server back event.
     * 
     * @return boolean
     */
    public boolean isServerBackEvent() {
        return eventStatus == EventState.SERVER_BACK;
    }

    /**
     * Check whether event is an arrival event.
     * 
     * @return boolean
     */
    public boolean isArriveEvent() {
        return eventStatus == EventState.ARRIVES;
    }

    /**
     * Check whether event is a serve event.
     * 
     * @return boolean
     */
    public boolean isServedEvent() {
        return eventStatus == EventState.SERVED;
    }

    /**
     * Check whether event is a wait event.
     * 
     * @return boolean
     */
    public boolean isWaitEvent() {
        return eventStatus == EventState.WAITS;
    }

    /**
     * Check whether event is a leave event.
     * 
     * @return boolean
     */
    public boolean isLeaveEvent() {
        return eventStatus == EventState.LEAVES;
    }

    /**
     * Check whether event is a done event.
     * 
     * @return boolean
     */
    public boolean isDoneEvent() {
        return eventStatus == EventState.DONE;
    }

    /**
     * Compare events.
     * 
     * @param event event
     * @return int for compareTo method
     */
    @Override
    public int compareTo(Event event) {
        if (Math.abs((time - event.getTime()) / time) < 1e-9) {
            if (isServerEvent()) {
                return -1;
            } else if (event.isServerEvent()) {
                return 1;
            } else {
                return customer.compareTo(event.getCustomer());
            }
        }

        if (time < event.getTime()) {
            return -1;
        } else if (time > event.getTime()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Override toString.
     * 
     * @return description
     */
    @Override
    public String toString() {
        if (isArriveEvent()) {
            return String.format("%.3f %s arrives", time, customer);
        } else if (isServedEvent()) {
            return String.format("%.3f %s served by %s", time, customer, server);
        } else if (isWaitEvent()) {
            return String.format("%.3f %s waits to be served by %s", time, customer, server);
        } else if (isLeaveEvent()) {
            return String.format("%.3f %s leaves", time, customer);
        } else if (isDoneEvent()) {
            return String.format("%.3f %s done serving by %s", time, customer, server);
        }

        return null;
    }
}
