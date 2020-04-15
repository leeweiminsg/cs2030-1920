package cs2030.simulator;

import java.util.LinkedList;

public class SelfCheckoutCounter extends Server {
    final static LinkedList<Customer> queue;

    static {
        queue = new LinkedList<>();
    }

    /**
     * Constructor.
     * 
     * @param id server id
     */
    SelfCheckoutCounter(int id, int queueCapacity, RandomGenerator rng) {
        super(id, queueCapacity, rng);
    }

    /**
     * Check whether server is idle.
     * 
     * @return boolean
     */
    @Override
    public boolean isIdle() {
        return currentCustomer == null;
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
     * Check whether server is resting.
     * 
     * @return boolean
     */
    @Override
    public boolean isResting() {
        return false;
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
     * Go back to work.
     * 
     */
    @Override
    public Customer back() {
        return null;
    }

    /**
     * Remove served customer.
     * 
     * @return next customer object to be served or null
     */
    @Override
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
        return String.format("self-check %d", id + 1);
    }
}