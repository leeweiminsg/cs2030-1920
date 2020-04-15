import java.util.stream.*;

public class q1 {
    static String pattern(int n) {
        return IntStream
                .rangeClosed(1,n)
                .mapToObj(p -> IntStream.iterate(p, m -> m >= 1, m -> m - 1))
                .map(s -> s.mapToObj(String::valueOf).reduce("", (a, b) -> a + b))
                .reduce("", (a, b) -> (a + b + "\n"));

        
    }
}
