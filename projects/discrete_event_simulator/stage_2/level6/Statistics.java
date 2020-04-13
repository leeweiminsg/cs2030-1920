class Statistics {
  private int numOfServedCustomers;
  private int numOfLeftCustomers;
  private double totalWaitingTime;

  Statistics() {
    numOfServedCustomers = 0;
    numOfLeftCustomers = 0;
    totalWaitingTime = 0;
  }

  /**
   * Increment number of served customers by one.
   * 
   */
  public void addServedCustomersByOne() {
    numOfServedCustomers++;
  }

  /**
   * Increment number of left customers by one.
   * 
   */
  public void addLeftCustomersByOne() {
    numOfLeftCustomers++;
  }

  public void displayStats() {
    System.out.print(toString());
  }

  public void addTotalWaitingTime(double time) {
    totalWaitingTime += time;
  }

  /**
   * Getter method for calculating average waiting time.
   * 
   * @return double
   */
  public double getAverageWaitTime() {
    return totalWaitingTime / numOfServedCustomers;
  }

  /**
   * Override toString.
   * 
   * @return String
   */
  @Override
  public String toString() {
    return String.format("[%.3f %d %d]", getAverageWaitTime(), numOfServedCustomers, numOfLeftCustomers);
  }
}