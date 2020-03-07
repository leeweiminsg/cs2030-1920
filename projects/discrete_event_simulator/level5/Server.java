public class Server {
    private double nextServiceTime = 0;
    private Customer waitingCustomer = null;
    private int numOfCustomers = 0;
    private int numOfServes = 0;
    private int numOfLeaves = 0;
    private double totalWaitingTime = 0;

    /**
     * Get waiting customer.
     * 
     * @return Customer customer object
     */
    public Customer getWaitingCustomer() {
        return waitingCustomer;
    }

    /**
     * Set waiting customer.
     * 
     * @param customer customer object
     */
    public void setWaitingCustomer(Customer customer) {
        waitingCustomer = customer;
    }

    /**
     * Check whether there is waiting customer.
     * 
     * @return boolean
     */
    public boolean hasWaitingCustomer() {
        return waitingCustomer != null;
    }

    /**
     * Check whether customer is waiting.
     * 
     * @param customer customer
     * @return boolean
     */
    public boolean isWaitingCustomer(Customer customer) {
        if (waitingCustomer == null) {
            return false;
        }

        return waitingCustomer.equals(customer);
    }

    /**
     * getter method for number of customers.
     * 
     * @return int
     */
    public int numOfCustomers() {
        return numOfCustomers;
    }

    /**
     * setter method for incrementing number of customers.
     * 
     */
    public void addCustomers() {
        numOfCustomers++;
    }

    /**
     * getter method for number of serves.
     * 
     * @return int
     */
    public int getNumOfServes() {
        return numOfServes;
    }

    /**
     * setter method for incrementing number of serves.
     * 
     */
    public void addServe() {
        numOfServes++;
    }

    /**
     * getter method for number of leaves.
     * 
     * @return int
     */
    public int getNumOfLeaves() {
        return numOfLeaves;
    }

    /**
     * setter method for incrementing number of leaves.
     * 
     */
    public void addLeave() {
        numOfLeaves++;
    }

    /**
     * increments next service time: every services takes 1 unit of time.
     * 
     */
    public void updateNextServiceTime() {
        nextServiceTime++;
    }

    /**
     * updates customer's time to the moment he will finish.
     * 
     * @param customer customer object
     */
    public void updateNextServiceTime(Customer customer) {
        nextServiceTime = customer.getTime() + 1;
    }

    /**
     * getter method for total waiting time.
     * 
     * @return double
     */
    public double getTotalWaitingTime() {
        return totalWaitingTime;
    }

    /**
     * updates total waiting time.
     * 
     * @param customer customer object
     */
    public void updateTotalWaitingTime(Customer customer) {
        totalWaitingTime += nextServiceTime - 1 - customer.getTime();
    }

    /**
     * getter method for calculating average waiting time.
     * 
     * @return double
     */
    public double getAverageWaitingTime() {
        return getTotalWaitingTime() / getNumOfServes();
    }

    /**
     * serves customer and updates accordingly.
     * 
     * @param customer customer object
     * @return Customer
     */
    public Customer serve(Customer customer) {
        if (customer.isServed()) {
            if (isWaitingCustomer(customer)) {
                setWaitingCustomer(null);
            }

            return customer.setDone(nextServiceTime);
        } else if (customer.isWaiting()) {
            updateTotalWaitingTime(customer);
            addServe();
            return customer.setServed(nextServiceTime - 1);
        } else if (customer.getTime() < nextServiceTime) {
            if (hasWaitingCustomer()) {
                addLeave();
                return customer.setLeft();
            }

            setWaitingCustomer(customer.setWait());
            updateNextServiceTime();
            return getWaitingCustomer();
        } else {
            updateNextServiceTime(customer);
            addServe();
            return customer.setServed();
        }
    }
}
