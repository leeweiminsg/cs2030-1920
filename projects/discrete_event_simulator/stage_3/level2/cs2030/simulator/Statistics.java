package cs2030.simulator;

public class Statistics {
  private int numOfServedCustomers;
  private int numOfLeftCustomers;
  private double totalWaitingTime;

  /**
   * Constructor.
   */
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

  /**
   * Add to total waiting time.
   * 
   */
  public void addTotalWaitingTime(double time) {
    totalWaitingTime += time;
  }

  /**
   * Getter method for calculating average waiting time.
   * 
   * @return double
   */
  public double getAverageWaitTime() {
    if (numOfServedCustomers == 0) {
      return 0;
    }

    return totalWaitingTime / numOfServedCustomers;
  }

  /**
   * Display stats.
   * 
   */
  public void displayStats() {
    System.out.println(toString());
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