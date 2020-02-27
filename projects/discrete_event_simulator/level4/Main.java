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

        System.out.println("# Adding arrivals");

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            Customer customer = new Customer(arrivalTime);
            System.out.println(customer);
            pq.add(customer);
            server.addCustomers();
        }

        System.out.println();

        // System.out.println(Arrays.toString(pq.toArray()));

        while (pq.peek() != null) {
            Customer customer = pq.poll();

            System.out.println("# Get next event: " + customer);

            if (!customer.hasLeft() && !customer.isDone()) {
                Customer updatedCustomer = server.serve(customer);

                pq.add(updatedCustomer);
            }

            Object[] pqArray = pq.toArray();
            Arrays.sort(pqArray);
            for (Object c : pqArray) {
                System.out.println(c);
            }

            System.out.println();

            // System.out.println(Arrays.toString(pq.toArray()));
        }

        System.out.println(String.format("Number of customers: %d", server.numOfCustomers()));

        sc.close();
    }
}
