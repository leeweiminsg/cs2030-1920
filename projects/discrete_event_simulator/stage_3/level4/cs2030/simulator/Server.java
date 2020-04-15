package cs2030.simulator;

public abstract class Server {
    final int id;
    final int queueCapacity;
    final RandomGenerator rng;
    Customer currentCustomer;
    double canServeNextAt;

    /**
     * Constructor.
     * 
     * @param id            server id
     * @param queueCapacity queue capacity
     * @param rng           random number generator
     */
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
     * Get queue length.
     * 
     * @return queue length
     */
    public abstract int getQueueLength();

    /**
     * Check whether server is resting.
     * 
     * @return boolean
     */
    public abstract boolean isResting();

    /**
     * Add customer to queue.
     * 
     * @param customer customer
     */
    public abstract void addToQueue(Customer customer);

    /**
     * Serve customer.
     * 
     * @param customer customer
     * @return updated customer
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
     * @return next customer object to be served or null
     */
    public abstract Customer back();

    /**
     * Override toString.
     * 
     * @return description
     */
    @Override
    public abstract String toString();
}
