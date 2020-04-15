import java.util.Scanner;

import cs2030.simulator.EventHandler;

public class Main {
    /**
     * Main method.
     * 
     * @param args arguments for command line
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int seed = Integer.parseInt(sc.next().replace("\"", ""));
        int numOfServers = Integer.parseInt(sc.next());
        int queueCapacity = Integer.parseInt(sc.next());
        int numOfCustomers = Integer.parseInt(sc.next());
        double arrivalRate = Double.parseDouble(sc.next());
        double serviceRate = Double.parseDouble(sc.next().replace("\"", ""));

        EventHandler eventHandler = new EventHandler(seed, numOfServers, queueCapacity, numOfCustomers, arrivalRate, serviceRate);

        eventHandler.processEvents();

        eventHandler.displayStats();

        sc.close();
    }
}