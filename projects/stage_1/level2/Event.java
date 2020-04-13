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
      return String.format("%s arrives at %.3f", customer, time);
    } else if (isServedEvent()) {
      return String.format("Customer served; next service @ %.3f", getNextServiceTime());
    }

    return null;
  }
}