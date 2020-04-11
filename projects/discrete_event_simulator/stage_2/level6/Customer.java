public class Customer implements Comparable<Customer> {
    public static final int ARRIVES = 1;
    public static final int SERVED = 2;
    public static final int LEAVES = 3;
    public static final int WAITS = 4;
    public static final int DONE = 5;

    private static int id = 1;

    private int customerState;
    private final int customerId;
    private double time;
    private int serverId = -1;

    /**
     * Constructor.
     * 
     * @param time time of event
     */
    public Customer(double time) {
        this.customerId = Customer.id++;
        this.time = time;
        this.customerState = Customer.ARRIVES;
    }

    /**
     * Get customer Id.
     * 
     * @return int
     * 
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Get customer state.
     * 
     * @return int
     */
    public int getState() {
        return customerState;
    }

    /**
     * Get event time.
     * 
     * @return double
     */
    public double getTime() {
        return time;
    }

    /**
     * Set event time.
     * 
     * @param time event time
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * Set server id.
     * 
     * @param serverId server id
     */
    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    /**
     * Check whether customer has server.
     * 
     * @return boolean
     */
    public boolean hasServer() {
        return serverId != -1;
    }

    /**
     * Get server id.
     * 
     * @return int server id
     */
    public int getServerId() {
        return serverId;
    }

    /**
     * Set customer state to served.
     * 
     * @return Customer updated customer
     */
    public Customer setServed(int serverId) {
        customerState = SERVED;
        setServerId(serverId);

        return this;
    }

    /**
     * Overloaded setServed, with event time to be set.
     * 
     * @param time event time
     * @return Customer updated customer
     */
    public Customer setServed(double time) {
        customerState = SERVED;
        this.time = time;

        return this;
    }

    /**
     * Set customer state to served.
     * 
     * @return Customer updated customer
     */
    public Customer setLeft() {
        customerState = LEAVES;

        return this;
    }

    /**
     * Set customer state to served.
     * 
     * @return Customer updated customer
     */
    public Customer setWait(int serverId) {
        customerState = WAITS;
        setServerId(serverId);

        return this;
    }

    /**
     * Set customer state to served, with event time to be set.
     * 
     * @return Customer updated customer
     */
    public Customer setDone() {
        customerState = DONE;
        this.time += 1;

        return this;
    }

    /**
     * Check where customer state is arrives.
     * 
     * @return boolean
     */
    public boolean isArrived() {
        return customerState == ARRIVES;
    }

    /**
     * Check where customer state is served.
     * 
     * @return boolean
     */
    public boolean isServed() {
        return customerState == SERVED;
    }

    /**
     * Check where customer state is leaves.
     * 
     * @return boolean
     */
    public boolean hasLeft() {
        return customerState == LEAVES;
    }

    /**
     * Check where customer state is done.
     * 
     * @return boolean
     */
    public boolean isDone() {
        return customerState == DONE;
    }

    /**
     * Check where customer state is waiting.
     * 
     * @return boolean
     */
    public boolean isWaiting() {
        return customerState == WAITS;
    }

    /**
     * Compare customers.
     * 
     * @param customer customer
     * @return int for compareTo method
     */
    @Override
    public int compareTo(Customer customer) {
        if (Math.abs((time - customer.getTime()) / time) < 1e-9) {
            if (customerId < customer.getCustomerId()) {
                return -1;
            } else if (customerId > customer.getCustomerId()) {
                return 1;
            }
        } else if (time < customer.getTime()) {
            return -1;
        } else if (time > customer.getTime()) {
            return 1;
        } else {
            return 0;
        }

        return 0;
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
     * Override toString.
     * 
     * @return String
     */
    @Override
    public String toString() {
        if (isArrived()) {
            return String.format("%.3f %d arrives", time, customerId);
        } else if (isServed()) {
            return String.format("%.3f %d served by %d", time, customerId, serverId);
        } else if (hasLeft()) {
            return String.format("%.3f %d leaves", time, customerId);
        } else if (isDone()) {
            return String.format("%.3f %d done serving by %d", time, customerId, serverId);
        } else {
            return String.format("%.3f %d waits to be served by %d", time, customerId, serverId);
        }
    }
}
