class Customer implements Comparable<Customer> {
    private static int id = 1;

    private enum customerState {
        ARRIVES, SERVED, LEAVES, WAITS, DONE
    }

    private final int customerId;
    private customerState customerStatus;

    /**
     * Constructor.
     */
    Customer() {
        customerId = Customer.id++;
        customerStatus = customerState.ARRIVES;
    }

    /**
     * Overloaded constructor.
     */
    Customer(int customerId, customerState customerStatus) {
        this.customerId = customerId;
        this.customerStatus = customerStatus;
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
     * Set customer state to served.
     * 
     * @return Customer updated customer
     */
    public Customer setServed() {
        return new Customer(customerId, customerState.SERVED);
    }

    /**
     * Check where customer state is arrives.
     * 
     * @return boolean
     */
    public boolean isArrived() {
        return customerStatus == customerState.ARRIVES;
    }

    /**
     * Check where customer state is served.
     * 
     * @return boolean
     */
    public boolean isServed() {
        return customerStatus == customerState.SERVED;
    }

    /**
     * Check where customer state is leaves.
     * 
     * @return boolean
     */
    public boolean hasLeft() {
        return customerStatus == customerState.LEAVES;
    }

    /**
     * Check where customer state is done.
     * 
     * @return boolean
     */
    public boolean isDone() {
        return customerStatus == customerState.DONE;
    }

    /**
     * Check where customer state is waiting.
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
