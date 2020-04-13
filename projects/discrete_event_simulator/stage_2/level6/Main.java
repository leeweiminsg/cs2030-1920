import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    /**
     * Main method.
     * 
     * @param args arguments for command line
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Double> timesArray = new ArrayList<>();

        int serverNum = sc.nextInt();
        EventHandler eventHandler = new EventHandler(serverNum);

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            timesArray.add(arrivalTime);
        }
        
        eventHandler.createArrivalEvents(timesArray);

        eventHandler.processEvents();

        eventHandler.displayStats();

        sc.close();
    }
}