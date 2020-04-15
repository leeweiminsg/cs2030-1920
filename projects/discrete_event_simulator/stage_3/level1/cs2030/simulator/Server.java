package cs2030.simulator;

import java.util.LinkedList;

public class Server {
  private final int id;
  private final LinkedList<Customer> queue;
  private Customer currentCustomer;
  private final RandomGenerator rng;
  private final int queueCapacity;
  private double canServeNextAt;

  /**
   * Constructor.
   * 
   * @param id server id
   */
  Server(int id, int queueCapacity, RandomGenerator rng) {
    this.id = id;
    queue = new LinkedList<>();
    currentCustomer = null;
    this.rng = rng;
    this.queueCapacity = queueCapacity;
    canServeNextAt = 0;
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
   * Get queue length.
   * 
   * @return queue length
   */
  public int getQueueLength() {
    return queue.size();
  }
  
  /**
   * Get next serve time.
   * 
   * @return next serve time
   */
  public double getNextServeTime() {
    return canServeNextAt;
  }

  /**
   * Set next serve time.
   * 
   */
  public void setNextServeTime(double servedTime) {
    canServeNextAt = servedTime;
  }

  /**
   * Check whether server is idle.
   * 
   * @return boolean
   */
  public boolean isIdle() {
    return currentCustomer == null && queue.isEmpty();
  }

  /**
   * Check whether queue is full.
   * 
   * @return boolean
   */
  public boolean isFull() {
    return getQueueLength() == queueCapacity;
  }

  /**
   * Serve customer.
   * 
   * @param Customer customer
   */
  public Customer serve(Customer customer) {
    currentCustomer = customer;
    canServeNextAt = customer.getTime() + rng.genServiceTime();

    return currentCustomer.setDone().setTime(canServeNextAt);
  }

  /**
   * Add customer to queue.
   * 
   */
  public void addToQueue(Customer customer) {
    queue.add(customer);
  }

  /**
   * Remove served customer.
   * 
   * @return next customer object to be served or null
   */
  public Customer done() {
    currentCustomer = null;

    if (!queue.isEmpty()) {
      return queue.removeFirst().setServed().setTime(canServeNextAt);
    }

    return null;
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