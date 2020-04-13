class Event implements Comparable<Event> {
  private final Customer customer;
  private final Server server;
  private final Double time;

  Event(Customer customer, double time) {
    this.customer = customer;
    this.time = time;
    server = null;
  }

  Event(Customer customer, double time, Server server) {
    this.customer = customer;
    this.time = time;
    this.server = server;
  }

  static Event serve(Event arriveEvent, Server server) {
    Customer customer = arriveEvent.getCustomer();
    Customer servedCustomer = customer.setServed();
    double arriveTime = arriveEvent.getTime();
    server.setNextServeTime(arriveTime);

    return new Event(servedCustomer, arriveTime, server);
  }

  static Event serve(Event arriveEvent, double serveTime, Server server) {
    Customer customer = arriveEvent.getCustomer();
    Customer servedCustomer = customer.setServed();

    return new Event(servedCustomer, serveTime, server);
  }

  static Event wait(Event arriveEvent, Server server) {
    Customer customer = arriveEvent.getCustomer();
    Customer waitingCustomer = customer.setWait();
    double arriveTime = arriveEvent.getTime();
    server.setNextServeTime();
    server.setWaitingCustomer();

    return new Event(waitingCustomer, arriveTime, server);
  }

  static Event leave(Event arriveEvent) {
    Customer customer = arriveEvent.getCustomer();
    Customer leftCustomer = customer.setLeave();
    double leaveTime = arriveEvent.getTime();

    return new Event(leftCustomer, leaveTime);
  }

  static Event done(Event servedEvent) {
    Customer customer = servedEvent.getCustomer();
    Customer doneCustomer = customer.setDone();
    double doneTime = servedEvent.getDoneTime();
    Server server = servedEvent.getServer();

    return new Event(doneCustomer, doneTime, server);
  }

  /**
   * Get customer.
   * 
   * @return customer object.
   */
  public Customer getCustomer() {
    return customer;
  }

  /**
   * Get server.
   * 
   * @return server object.
   */
  public Server getServer() {
    return server;
  }

  /**
   * Get event time.
   * 
   * @return double
   */
  public double getTime() {
    return time;
  }

  /**
   * Get done time if applicable.
   * 
   * @return double
   */
  double getDoneTime() {
    return server.getNextServeTime();
  }

  public boolean isArriveEvent() {
    return customer.isArrived();
  }

  public boolean isServedEvent() {
    return customer.isServed();
  }

  public boolean isWaitEvent() {
    return customer.isWaiting();
  }

  public boolean isLeaveEvent() {
    return customer.hasLeft();
  }

  public boolean isDoneEvent() {
    return customer.isDone();
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
      return customer.compareTo(event.getCustomer());
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
   * @return String
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