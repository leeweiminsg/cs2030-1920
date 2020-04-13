class Server {
  private int id;
  private double nextServeAt;
  private boolean hasWaitingCustomer;

  /**
   * Constructor.
   * 
   * @param id server id
   */
  Server(int id) {
    this.id = id;
    nextServeAt = 0;
    hasWaitingCustomer = false;
  }

  /**
   * Get server id.
   * 
   * @return server id
   */
  public int getId() {
    return id;
  }
  
  /**
   * Get next serve time.
   * 
   * @return next serve time
   */
  public double getNextServeTime() {
    return nextServeAt;
  }
  
  /**
   * Set next serve time.
   * 
   */
  public void setNextServeTime() {
    nextServeAt++;
  }

  /**
   * Set next serve time.
   * 
   */
  public void setNextServeTime(double arriveTime) {
    nextServeAt = arriveTime + 1;
  }

  public boolean hasWaitingCustomer() {
    return hasWaitingCustomer;
  }

  public void setWaitingCustomer() {
    hasWaitingCustomer = true;
  }

  public void removeWaitingCustomer() {
    hasWaitingCustomer = false;
  }

  /**
   * Override toString.
   * 
   * @return String
   */
  @Override
  public String toString() {
    return Integer.toString(id);
  }
}