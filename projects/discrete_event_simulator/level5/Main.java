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
        }

        // System.out.println(Arrays.toString(pq.toArray()));

        while (pq.peek() != null) {
            Customer customer = pq.poll();

            System.out.println(customer);

            if (!customer.hasLeft() && !customer.isDone()) {
                Customer updatedCustomer = server.serve(customer);

                pq.add(updatedCustomer);
            }

            // System.out.println(Arrays.toString(pq.toArray()));
        }

        double avWaitingTime = server.getAverageWaitingTime();
        int numOfServes = server.getNumOfServes();
        int numOfLeaves = server.getNumOfLeaves();
        System.out.println(String.format("[%.3f %d %d]", avWaitingTime, numOfServes, numOfLeaves));

        sc.close();
    }
}
