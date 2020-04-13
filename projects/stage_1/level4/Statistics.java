class Statistics {
  private int numOfCustomers;

  Statistics() {
    numOfCustomers = 0;
  }

  /**
   * Increment number of customers by one.
   * 
   */
  public void addCustomersByOne() {
    numOfCustomers++;
  }

  public void displayStats() {
    System.out.print(toString());
  }

  /**
   * Override toString.
   * 
   * @return String
   */
  @Override
  public String toString() {
    return String.format("Number of customers: %d", numOfCustomers);
  }
}