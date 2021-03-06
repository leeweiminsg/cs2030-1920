class Event implements Comparable<Event> {
  private final Customer customer;
  private final Double time;

  Event(Customer customer, double time) {
    this.customer = customer;
    this.time = time;
  }

  static Event serve(Event arriveEvent) {
    Customer customer = arriveEvent.getCustomer();
    Customer servedCustomer = customer.setServed();
    double arriveTime = arriveEvent.getTime();

    return new Event(servedCustomer, arriveTime);
  }

  static Event leave(Event arriveEvent) {
    Customer customer = arriveEvent.getCustomer();
    Customer leftCustomer = customer.setLeave();
    double leaveTime = arriveEvent.getTime();

    return new Event(leftCustomer, leaveTime);
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
   * Get event time.
   * 
   * @return double
   */
  public double getTime() {
    return time;
  }

  /**
   * Get next servie time if applicable.
   * 
   * @return double
   */
  double getNextServiceTime() {
    return time + 1;
  }

  public boolean isArriveEvent() {
    return customer.isArrived();
  }

  public boolean isServedEvent() {
    return customer.isServed();
  }

  public boolean isLeaveEvent() {
    return customer.hasLeft();
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
      return String.format("%.3f %s served", time, customer);
    } else if (isLeaveEvent()) {
      return String.format("%.3f %s leaves", time, customer);
    }

    return null;
  }
}