import java.util.stream.Stream;
import java.util.NoSuchElementException;

class Main {
    public static double normalizedMean(Stream<Integer> stream) {
        Stream<Statistics> statsSteam = stream.map(n -> new Statistics(n, n, n, 1));

        try {
            Statistics result = statsSteam.reduce(
                (s1, s2) -> new Statistics(
                        Math.min(s1.min, s2.min),
                        Math.max(s1.max, s2.max), 
                        s1.sum + s2.sum, 
                        s1.count + s2.count))
                    .get();

            if (result.count == 0 || result.max == result.min) {
                return 0;
            }

            double ans = (((double) result.sum / result.count) - result.min)
                / (result.max - result.min);

            return ans;
        } catch (NoSuchElementException e) {
            return 0;
        }
    }
}
