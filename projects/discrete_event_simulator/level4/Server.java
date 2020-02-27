public class Server {
    private double nextServiceTime = 0;
    private Customer waitingCustomer = null;
    private int numOfCustomers = 0;
    private int numOfServes = 0;
    private int numOfLeaves = 0;
    private double totalWaitingTime = 0;

    /**
     * @return Customer
     */
    public Customer getWaitingCustomer() {
        return waitingCustomer;
    }

    /**
     * @param customer
     */
    public void setWaitingCustomer(Customer customer) {
        waitingCustomer = customer;
    }

    /**
     * @return boolean
     */
    public boolean hasWaitingCustomer() {
        return waitingCustomer != null;
    }

    /**
     * @param customer
     * @return boolean
     */
    public boolean isWaitingCustomer(Customer customer) {
        if (waitingCustomer == null) {
            return false;
        }

        return waitingCustomer.equals(customer);
    }

    public int numOfCustomers() {
        return numOfCustomers;
    }

    public void addCustomers() {
        numOfCustomers++;
    }

    /**
     * @return int
     */
    public int getNumOfServes() {
        return numOfServes;
    }

    public void addServe() {
        numOfServes++;
    }

    /**
     * @return int
     */
    public int getNumOfLeaves() {
        return numOfLeaves;
    }

    public void addLeave() {
        numOfLeaves++;
    }

    public void updateNextServiceTime() {
        nextServiceTime++;
    }

    /**
     * @param customer
     */
    public void updateNextServiceTime(Customer customer) {
        nextServiceTime = customer.getTime() + 1;
    }

    /**
     * @return double
     */
    public double getTotalWaitingTime() {
        return totalWaitingTime;
    }

    /**
     * @param customer
     */
    public void updateTotalWaitingTime(Customer customer) {
        totalWaitingTime += nextServiceTime - 1 - customer.getTime();
    }

    /**
     * @return double
     */
    public double getAverageWaitingTime() {
        return getTotalWaitingTime() / getNumOfServes();
    }

    /**
     * @param customer
     * @return Customer
     */
    public Customer serve(Customer customer) {
        if (customer.isServed()) {
            return customer.setDone();
        } else if (customer.getTime() < nextServiceTime) {
            return customer.setLeft();
        } else {
            updateNextServiceTime(customer);
            return customer.setServed();
        }
    }
}
