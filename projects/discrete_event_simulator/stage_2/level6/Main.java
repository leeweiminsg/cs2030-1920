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
        PriorityQueue<Customer> pq = new PriorityQueue<>();

        int serverNum = sc.nextInt();

        Server.createServers(serverNum);

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            Customer customer = new Customer(arrivalTime);
            pq.add(customer);
        }

        while (pq.peek() != null) {
            Customer customer = pq.poll();

            System.out.println(customer);

            Server.processCustomer(customer, pq);
        }

        System.out.println(
                String.format("[%.3f %d %d]", 
                        Server.getAvWaitTime(), 
                        Server.getServes(), 
                        Server.getLeaves()));

        sc.close();
    }
}
