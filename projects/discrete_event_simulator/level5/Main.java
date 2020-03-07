import java.util.Scanner;
import java.util.PriorityQueue;

public class Main {

    /**
     * Main method.
     * 
     * @param args arguments for command line
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Server server = new Server();
        PriorityQueue<Customer> pq = new PriorityQueue<>();

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            Customer customer = new Customer(arrivalTime);
            pq.add(customer);
        }

        while (pq.peek() != null) {
            Customer customer = pq.poll();

            System.out.println(customer);

            if (!customer.hasLeft() && !customer.isDone()) {
                Customer updatedCustomer = server.serve(customer);

                pq.add(updatedCustomer);
            }

        }

        double avWaitingTime = server.getAverageWaitingTime();
        int numOfServes = server.getNumOfServes();
        int numOfLeaves = server.getNumOfLeaves();
        System.out.println(String.format("[%.3f %d %d]", avWaitingTime, numOfServes, numOfLeaves));

        sc.close();
    }
}
