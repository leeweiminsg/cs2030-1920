package cs2030.simulator;

import java.util.LinkedList;

public class HumanServer extends Server {
    private final LinkedList<Customer> queue;
    private double restingProbability;
    private boolean isResting;

    /**
     * Constructor.
     * 
     * @param id server id
     */
    HumanServer(int id, int queueCapacity, double restingProbability, RandomGenerator rng) {
        super(id, queueCapacity, rng);
        queue = new LinkedList<>();
        this.restingProbability = restingProbability;
        isResting = false;
    }

    /**
     * Check whether queue is full.
     * 
     * @return boolean
     */
    @Override
    public boolean isFull() {
        return queue.size() == queueCapacity;
    }

    /**
     * Add customer to queue.
     * 
     */
    @Override
    public void addToQueue(Customer customer) {
        queue.add(customer);
    }

    /**
     * Check whether server will rest.
     * 
     * @return boolean
     */
    boolean willRest() {
        double p = rng.genRandomRest();

        return p < restingProbability;
    }

    /**
     * Check whether server is resting.
     * 
     * @return boolean
     */
    @Override
    public boolean isResting() {
        return isResting;
    }

    /**
    * Check whether server is idle.
    * 
    * @return boolean
    */
    @Override
    public boolean isIdle() {
        return currentCustomer == null && queue.isEmpty() && !isResting;
    }

    /**
     * Remove served customer.
     * 
     * @return next customer object to be served or null
     */
    @Override
    public Customer done() {
        currentCustomer = null;

        if (willRest()) {
            rest();

            return null;
        }

        if (!queue.isEmpty()) {
            return queue.removeFirst().setServed().setTime(canServeNextAt);
        }

        return null;
    }

    /**
     * Rest.
     * 
     */
    void rest() {
        isResting = true;

        canServeNextAt += rng.genRestPeriod();
    }

    /**
     * Go back to work.
     * 
     */
    @Override
    public Customer back() {
        isResting = false;

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
        return String.format("server %d", id);
    }
}