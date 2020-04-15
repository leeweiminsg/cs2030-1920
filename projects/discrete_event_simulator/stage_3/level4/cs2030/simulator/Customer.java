package cs2030.simulator;

public class Customer implements Comparable<Customer> {
    private static int id = 1;

    private enum CustomerState {
        ARRIVES, SERVED, LEAVES, WAITS, DONE
    }

    private final int customerId;
    private final boolean isGreedy;
    private final CustomerState customerStatus;
    private final double arrivalTime;
    private final double time;

    /**
     * Constructor.
     * 
     * @param time     time
     * @param isGreedy whether customer is greedy
     */
    Customer(double time, boolean isGreedy) {
        customerId = Customer.id++;
        this.isGreedy = isGreedy;
        customerStatus = CustomerState.ARRIVES;
        arrivalTime = time;
        this.time = time;
    }

    /**
     * Overloaded constructor.
     * 
     * @param customerId     customer id
     * @param isGreedy       whether customer is greedy
     * @param customerStatus customer status
     * @param arrivalTime    arrival time
     * @param time           time
     */
    Customer(int customerId, boolean isGreedy, 
            CustomerState customerStatus, double arrivalTime, double time) {
        this.customerId = customerId;
        this.isGreedy = isGreedy;
        this.customerStatus = customerStatus;
        this.arrivalTime = arrivalTime;
        this.time = time;
    }

    /**
     * Factory method for constructing normal customer object.
     * 
     * @param time time
     * @return customer
     */
    static Customer createNormalCustomer(double time) {
        return new Customer(time, false);
    }

    /**
     * Factory method for constructing greedy customer object.
     * 
     * @param time time
     * @return customer
     */
    static Customer createGreedyCustomer(double time) {
        return new Customer(time, true);
    }

    /**
     * Checks whether greedy customer can be created.
     * 
     * @param RandomGenerator           rng
     * @param greedyCustomerProbability probability of greedy customer
     * 
     * @return boolean
     */
    static boolean canCreateGreedyCustomer(RandomGenerator rng, double greedyCustomerProbability) {
        double p = rng.genCustomerType();

        return p < greedyCustomerProbability;
    }

    /**
     * Get customer ID.
     * 
     * @return customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Check whether customer is greedy.
     * 
     * @return boolean
     */
    public boolean isGreedy() {
        return isGreedy;
    }

    /**
     * Get customer time.
     * 
     * @return time
     */
    public double getTime() {
        return time;
    }

    /**
     * Get customer arrival time.
     * 
     * @return customer arrival time
     */
    public double getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Get customer waiting time.
     * 
     * @return waiting time
     */
    public double getWaitingTime() {
        return time - arrivalTime;
    }

    /**
     * Set customer time.
     * 
     * @param newTime new customer time
     * @return customer
     */
    public Customer setTime(double newTime) {
        return new Customer(customerId, isGreedy, customerStatus, arrivalTime, newTime);
    }

    /**
     * Set customer state to served.
     * 
     * @return Customer
     */
    public Customer setServed() {
        return new Customer(customerId, isGreedy, CustomerState.SERVED, arrivalTime, time);
    }

    /**
     * Set customer state to waits.
     * 
     * @return Customer
     */
    public Customer setWait() {
        return new Customer(customerId, isGreedy, CustomerState.WAITS, arrivalTime, time);
    }

    /**
     * Set customer state to leaves.
     * 
     * @return Customer
     */
    public Customer setLeave() {
        return new Customer(customerId, isGreedy, CustomerState.LEAVES, arrivalTime, time);
    }

    /**
     * Set customer state to done.
     * 
     * @return Customer
     */
    public Customer setDone() {
        return new Customer(customerId, isGreedy, CustomerState.DONE, arrivalTime, time);
    }

    /**
     * Check whether customer state is arrives.
     * 
     * @return boolean
     */
    public boolean isArrived() {
        return customerStatus == CustomerState.ARRIVES;
    }

    /**
     * Check whether customer state is served.
     * 
     * @return boolean
     */
    public boolean isServed() {
        return customerStatus == CustomerState.SERVED;
    }

    /**
     * Check whether customer state is leaves.
     * 
     * @return boolean
     */
    public boolean hasLeft() {
        return customerStatus == CustomerState.LEAVES;
    }

    /**
     * Check whether customer state is done.
     * 
     * @return boolean
     */
    public boolean isDone() {
        return customerStatus == CustomerState.DONE;
    }

    /**
     * Check whether customer state is waiting.
     * 
     * @return boolean
     */
    public boolean isWaiting() {
        return customerStatus == CustomerState.WAITS;
    }

    /**
     * Override compareTo.
     * 
     * @param customer customer
     * @return int
     */
    @Override
    public int compareTo(Customer customer) {
        return customerId - customer.getCustomerId();
    }

    /**
     * Override toString.
     * 
     * @return description
     */
    @Override
    public String toString() {
        return customerId + (isGreedy ? "(greedy)" : "");
    }
}