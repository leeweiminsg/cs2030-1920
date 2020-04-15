package cs2030.simulator;

import java.util.LinkedList;

public class SelfCheckoutCounter extends Server {
    static LinkedList<Customer> queue;

    static {
        queue = new LinkedList<>();
    }

    /**
     * Constructor.
     * 
     * @param id            server id
     * @param queueCapacity queue capacity
     * @param rng           random number generator
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
     * Get queue length.
     * 
     * @return queue length
     */
    @Override
    public int getQueueLength() {
        return queue.size();
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
     */
    @Override
    public void addToQueue(Customer customer) {
        queue.add(customer);
    }

    /**
     * Go back to work.
     * 
     * @return customer
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
     * @return description
     */
    @Override
    public String toString() {
        return String.format("self-check %d", id + 1);
    }
}