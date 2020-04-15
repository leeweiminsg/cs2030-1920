package cs2030.simulator;

public class Customer implements Comparable<Customer> {
    private static int id = 1;

    private enum customerState {
        ARRIVES, SERVED, LEAVES, WAITS, DONE
    }

    private final int customerId;
    private final customerState customerStatus;
    private final double arrivalTime;
    private final double time;

    /**
     * Constructor.
     * 
     * @param double time
     */
    Customer(double time) {
        customerId = Customer.id++;
        customerStatus = customerState.ARRIVES;
        arrivalTime = time;
        this.time = time;
    }

    /**
     * Overloaded constructor.
     */
    Customer(int customerId, customerState customerStatus, double arrivalTime, double time) {
        this.customerId = customerId;
        this.customerStatus = customerStatus;
        this.arrivalTime = arrivalTime;
        this.time = time;
    }

    /**
     * Get customer ID.
     * 
     * @return int
     * 
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Get customer time.
     * 
     * @return double time
     * 
     */
    public double getTime() {
        return time;
    }

    /**
     * Get customer arrival time.
     * 
     * @return double time
     * 
     */
    public double getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Get customer waiting time (if he was waiting).
     * 
     * @return double time
     * 
     */
    public double getWaitingTime() {
        return time - arrivalTime;
    }

    /**
     * Set customer time.
     * 
     * @return Customer updated customer
     */
    public Customer setTime(double newTime) {
        return new Customer(customerId, customerStatus, arrivalTime, newTime);
    }

    /**
     * Set customer state to served.
     * 
     * @return Customer updated customer
     */
    public Customer setServed() {
        return new Customer(customerId, customerState.SERVED, arrivalTime, time);
    }

    /**
     * Set customer state to waits.
     * 
     * @return Customer updated customer
     */
    public Customer setWait() {
        return new Customer(customerId, customerState.WAITS, arrivalTime, time);
    }

    /**
     * Set customer state to leaves.
     * 
     * @return Customer updated customer
     */
    public Customer setLeave() {
        return new Customer(customerId, customerState.LEAVES, arrivalTime, time);
    }

    /**
     * Set customer state to done.
     * 
     * @return Customer updated customer
     */
    public Customer setDone() {
        return new Customer(customerId, customerState.DONE, arrivalTime, time);
    }

    /**
     * Check whether customer state is arrives.
     * 
     * @return boolean
     */
    public boolean isArrived() {
        return customerStatus == customerState.ARRIVES;
    }

    /**
     * Check whether customer state is served.
     * 
     * @return boolean
     */
    public boolean isServed() {
        return customerStatus == customerState.SERVED;
    }

    /**
     * Check whether customer state is leaves.
     * 
     * @return boolean
     */
    public boolean hasLeft() {
        return customerStatus == customerState.LEAVES;
    }

    /**
     * Check whether customer state is done.
     * 
     * @return boolean
     */
    public boolean isDone() {
        return customerStatus == customerState.DONE;
    }

    /**
     * Check whether customer state is waiting.
     * 
     * @return boolean
     */
    public boolean isWaiting() {
        return customerStatus == customerState.WAITS;
    }

    /**
     * Override equals.
     * 
     * @param obj object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        Customer customer = (Customer) obj;

        return customerId == customer.getCustomerId();
    }

    /**
     * Override compareTo.
     * 
     * @param customer customer
     * @return int for compareTo method
     */
    @Override
    public int compareTo(Customer customer) {
        return customerId - customer.getCustomerId();
    }

    /**
     * Override toString.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return Integer.toString(customerId);
    }
}