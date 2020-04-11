import java.util.ArrayList;
import java.util.PriorityQueue;

public class Server {
    public static ArrayList<Server> serverArray = new ArrayList<>();
    public static int numOfCustomers = 0;
    public static int numOfServes = 0;
    public static int numOfLeaves = 0;
    public static double totalWaitingTime = 0;

    private int id;
    private Customer servingCustomer = null;
    private Customer waitingCustomer = null;

    /**
     * Constructor.
     * 
     * @param id server id
     */
    Server(int id) {
        this.id = id;
    }

    /**
     * Get server id.
     * 
     * @return server id
     */
    public int getId() {
        return id;
    }

    /**
     * Create servers.
     * 
     * @param n number of servers
     */
    public static void createServers(int n) {
        for (int i = 0; i < n; i++) {
            serverArray.add(new Server(i + 1));
        }
    }

    /**
     * Process customer.
     * 
     * @param customer customer object
     * @param pq priority queue of customers
     */
    public static void processCustomer(Customer customer, PriorityQueue<Customer> pq) {
        if (!customer.hasLeft()) {
            if (customer.isArrived()) {
                boolean isManaged = false;

                for (Server server : serverArray) {
                    if (server.isIdle()) {
                        pq.add(server.serveArrived(customer));
                        isManaged = true;
                        break;
                    }
                }

                if (!isManaged) {
                    for (Server server : serverArray) {
                        if (!server.hasWaitingCustomer()) {
                            pq.add(server.makeWaitArrived(customer));
                            isManaged = true;
                            break;
                        }
                    }
                }

                if (!isManaged) {
                    pq.add(Server.makeLeave(customer));
                }
            } else if (customer.isServed()) {
                for (Server server : serverArray) {
                    if (server.hasServingCustomer(customer)) {
                        pq.add(server.serveServed(customer));
                    }
                }
            } else if (customer.isWaiting()) {
                for (Server server : serverArray) {
                    if (server.hasWaitingCustomer(customer)) {
                        pq.add(server.serveWaiting(customer));
                    }
                }
            } else if (customer.isDone()) {
                for (Server server : serverArray) {
                    if (server.hasServingCustomer(customer)) {
                        server.serveDone(customer);
                    }
                }
            }
        }
    }

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

        totalWaitingTime += servingCustomer.getTime() - waitingCustomer.getTime();
    }

    /**
     * Remove waiting customer.
     * 
     */
    public void removeWaitingCustomer() {
        waitingCustomer = null;
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
     * Check whether server has that waiting customer.
     * 
     * @param customer customer object
     * @return boolean
     */
    public boolean hasWaitingCustomer(Customer customer) {
        return waitingCustomer == customer;
    }

    /**
     * Get serving customer.
     * 
     * @return Customer customer object
     */
    public Customer getServingCustomer() {
        return servingCustomer;
    }

    /**
     * Set serving customer.
     * 
     * @param customer customer object
     */
    public void setServingCustomer(Customer customer) {
        servingCustomer = customer;
    }

    /**
     * Remove serving customer.
     * 
     */
    public void removeServingCustomer() {
        servingCustomer = null;
    }

    /**
     * Convert waiting customer to served in server.
     * 
     */
    public void convertWaitingCustomer() {
        waitingCustomer.setServed(servingCustomer.getTime());
    }

    /**
     * Check whether server has serving customer.
     * 
     * @return boolean
     */
    public boolean hasServingCustomer() {
        return servingCustomer != null;
    }

    /**
     * Check whether server has that serving customer.
     * 
     * @param customer customer object
     * @return boolean
     */
    public boolean hasServingCustomer(Customer customer) {
        return servingCustomer == customer;
    }

    /**
     * Check whether server is idle.
     * 
     * @return boolean
     */
    public boolean isIdle() {
        return !hasServingCustomer();
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
    public static int getServes() {
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
    public static int getLeaves() {
        return numOfLeaves;
    }

    /**
     * setter method for incrementing number of leaves.
     * 
     */
    public static void addLeave() {
        numOfLeaves++;
    }

    /**
     * getter method for total waiting time.
     * 
     * @return double
     */
    public static double getTotalWaitingTime() {
        return totalWaitingTime;
    }

    /**
     * getter method for calculating average waiting time.
     * 
     * @return double
     */
    public static double getAvWaitTime() {
        return getTotalWaitingTime() / getServes();
    }

    /**
     * Set arrived customer to leave, updates leave counter.
     * 
     * @param customer customer object
     * @return updated customer
     */
    public static Customer makeLeave(Customer customer) {
        addLeave();

        return customer.setLeft();
    }

    /**
     * Set arrived customer to served, updates serve counter.
     * 
     * @param customer customer object
     * @return updated customer
     */
    public Customer serveArrived(Customer customer) {
        setServingCustomer(customer);
        addServe();

        return customer.setServed(getId());
    }

    /**
     * Set served customer to done.
     * 
     * @param customer customer object
     * @return updated customer
     */
    public Customer serveServed(Customer customer) {
        return customer.setDone();
    }

    /**
     * removes done customer from server and updates waiting customer to being served.
     * 
     * @param customer customer object
     */
    public void serveDone(Customer customer) {
        removeServingCustomer();
        setServingCustomer(getWaitingCustomer());
        removeWaitingCustomer();
    }

    /**
     * Set waiting customer to done.
     * 
     * @param customer customer object
     * @return updated customer
     */
    public Customer serveWaiting(Customer customer) {
        addServe();
        convertWaitingCustomer();

        return getWaitingCustomer();
    }

    /**
     * Set customer to wait and updates in server.
     * 
     * @param customer customer object
     * @return updated customer
     */
    public Customer makeWaitArrived(Customer customer) {
        setWaitingCustomer(customer);

        return customer.setWait(getId());
    }

    /**
     * Override toString, for testing purposes.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return String.format(
                "Id: %d, servingCustomer: %s, waitingCustomer: %s", 
                id, 
                servingCustomer, 
                waitingCustomer);
    }
}
