public class Customer implements Comparable<Customer> {
    public static final int ARRIVES = 1;
    public static final int SERVED = 2;
    public static final int LEAVES = 3;
    public static final int DONE = 4;
    public static final int WAITS = 5;

    private static int id = 1;

    private int customerState;
    private final int customerId;
    private double time;

    public Customer(double time) {
        this.customerId = Customer.id++;
        this.time = time;
        this.customerState = Customer.ARRIVES;
    }

    /**
     * @return int
     * 
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @return int
     */
    public int getState() {
        return customerState;
    }

    /**
     * @return double
     */
    public double getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * @return Customer
     */
    public Customer setServed() {
        customerState = SERVED;
        return this;
    }

    /**
     * @param time
     * @return Customer
     */
    public Customer setServed(double time) {
        customerState = SERVED;
        this.time = time;
        return this;
    }

    /**
     * @return Customer
     */
    public Customer setLeft() {
        customerState = LEAVES;
        return this;
    }

    /**
     * @return Customer
     */
    public Customer setWait() {
        customerState = WAITS;
        return this;
    }

    public Customer setDone() {
        customerState = DONE;
        time += 1;
        return this;
    }

    /**
     * @param time
     * @return Customer
     */
    public Customer setDone(double time) {
        customerState = DONE;
        this.time = time;

        return this;
    }

    /**
     * @return boolean
     */
    public boolean isArrived() {
        return customerState == ARRIVES;
    }

    /**
     * @return boolean
     */
    public boolean isServed() {
        return customerState == SERVED;
    }

    /**
     * @return boolean
     */
    public boolean hasLeft() {
        return customerState == LEAVES;
    }

    /**
     * @return boolean
     */
    public boolean isDone() {
        return customerState == DONE;
    }

    /**
     * @return boolean
     */
    public boolean isWaiting() {
        return customerState == WAITS;
    }

    /**
     * @param customer
     * @return int
     */
    @Override
    public int compareTo(Customer customer) {
        if (time < customer.getTime()) {
            return -1;
        } else if (time > customer.getTime()) {
            return 1;
        } else if (customerId < customer.getCustomerId()) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        Customer customer = (Customer) obj;

        return customerId == customer.getCustomerId();
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        if (isArrived()) {
            return String.format("%.3f %d arrives", time, customerId);
        } else if (isServed()) {
            return String.format("%.3f %d served", time, customerId);
        } else if (hasLeft()) {
            return String.format("%.3f %d leaves", time, customerId);
        } else if (isDone()) {
            return String.format("%.3f %d done", time, customerId);
        } else {
            return String.format("%.3f %d waits", time, customerId);
        }
    }
}
