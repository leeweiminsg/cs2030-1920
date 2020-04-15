package cs2030.simulator;

public abstract class Server {
  final int id;
  final int queueCapacity;
  final RandomGenerator rng;
  Customer currentCustomer;
  double canServeNextAt;

  Server(int id, int queueCapacity, RandomGenerator rng) {
    this.id = id;
    this.queueCapacity = queueCapacity;
    this.rng = rng;
    currentCustomer = null;
    canServeNextAt = 0;
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
   * Check whether server is idle.
   * 
   * @return boolean
   */
  public abstract boolean isIdle();

  /**
   * Check whether queue is full.
   * 
   * @return boolean
   */
  public abstract boolean isFull();

  /**
   * Check whether server is resting.
   * 
   * @return boolean
   */
  public abstract boolean isResting();

  /**
   * Add customer to queue.
   * 
   */
  public abstract void addToQueue(Customer customer);

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
   * Remove served customer.
   * 
   * @return next customer object to be served or null
   */
  public abstract Customer done();

  /**
   * Go back to work.
   * 
   */
  public abstract Customer back();

  /**
   * Override toString.
   * 
   * @return String
   */
  @Override
  public abstract String toString();
}