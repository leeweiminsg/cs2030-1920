import java.util.Scanner;
import java.util.PriorityQueue;

import java.util.Arrays;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Server server = new Server();
        PriorityQueue<Customer> pq = new PriorityQueue<>();

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            Customer customer = new Customer(arrivalTime);
            pq.add(customer);
            server.addCustomers();
        }

        // System.out.println(Arrays.toString(pq.toArray()));

        while (pq.peek() != null) {
            Customer customer = pq.poll();

            System.out.println(customer);

            // System.out.println(Arrays.toString(pq.toArray()));
        }

        System.out.println(String.format("Number of customers: %d", server.numOfCustomers()));

        sc.close();
    }
}
