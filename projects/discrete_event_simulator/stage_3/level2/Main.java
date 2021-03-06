import java.util.Scanner;

import cs2030.simulator.EventHandler;
import cs2030.simulator.Customer;
import cs2030.simulator.Event;
import cs2030.simulator.Server;
import cs2030.simulator.Statistics;

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
        double serviceRate = Double.parseDouble(sc.next());
        double restingRate = Double.parseDouble(sc.next());
        double restingProbability = Double.parseDouble(sc.next().replace("\"", ""));

        EventHandler eventHandler = new EventHandler(seed, numOfServers, queueCapacity, numOfCustomers, arrivalRate, serviceRate, restingRate, restingProbability);

        eventHandler.processEvents();

        eventHandler.displayStats();

        sc.close();
    }
}