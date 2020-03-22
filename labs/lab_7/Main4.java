import java.util.stream.Stream;
import java.util.NoSuchElementException;

class Main {
    public static double normalizedMean(Stream<Integer> stream) {
        Stream<Statistics> statsSteam = stream.map(n -> new Statistics(n, n, n, 1));

        try {
            Statistics result = statsSteam.reduce(
                (s1, s2) -> new Statistics(
                        Math.min(s1.getMin(), s2.getMin()),
                        Math.max(s1.getMax(), s2.getMax()), 
                        s1.getSum() + s2.getSum(), 
                        s1.getCount() + s2.getCount())).get();

            double ans = (((double) result.getSum() / result.getCount()) - result.getMin())
                / (result.getMax() - result.getMin());

            if (Double.isNaN(ans)) {
                return 0;
            }

            return ans;
        } catch (NoSuchElementException e) {
            return 0;
        }
    }
}
