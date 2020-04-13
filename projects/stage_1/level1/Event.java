class Event implements Comparable<Event> {
  private final Customer customer;
  private final Double time;

  Event(Customer customer, double time) {
    this.customer = customer;
    this.time = time;
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
    return String.format("%s arrives at %.3f", customer, time);
  }
}