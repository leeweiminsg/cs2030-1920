import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.lang.InterruptedException;
import java.util.concurrent.ExecutionException;

/**
 * This program finds different ways one can travel by bus (with a bit of
 * walking) from one bus stop to another.
 *
 * @author: Ooi Wei Tsang
 * @version: CS2030 AY19/20 Semester 1, Lab 10
 */
public class Main {
    /**
     * The program read a sequence of (id, search string) from standard input.
     * 
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Instant start = Instant.now();
        Scanner sc = new Scanner(System.in);
        List<CompletableFuture<String>> arrayList = new ArrayList<>();

        while (sc.hasNext()) {
            BusStop srcId = new BusStop(sc.next());
            String searchString = sc.next();

            arrayList.add(BusSg
                            .findBusServicesBetween(srcId, searchString)
                            .thenCompose(route -> route.description()));
        }

        CompletableFuture.allOf(arrayList.toArray(new CompletableFuture<?>[0])).join();

        for (CompletableFuture<String> routes : arrayList) {      
            try {
                System.out.println(routes.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        sc.close();
        Instant stop = Instant.now();
        System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
    }
}
