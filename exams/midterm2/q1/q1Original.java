import java.util.stream.*;

public class q1 {
    static String pattern(int n) {
        return IntStream.rangeClosed(1, n).map(m -> IntStream.iterate(m, m >= 1, m -> m - 1))
                .flatmap(s -> s.reduce("", (a, b) -> a + b)).reduce("", (a, b) -> (a + b + "/n"));

    }
}