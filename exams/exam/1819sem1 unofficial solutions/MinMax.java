public class MinMax {
    final int min, max;

    public MinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return min + ", " + max;
    }

    static Optional<MinMax> findMinMax(Stream<Integer> intStream) {
        Optional<MinMax> ans = intStream.map(x -> new MinMax(x, x)).reduce((x, y) -> {
            Integer max = x.max;
            Integer min = x.min;
            if (y.min < min) {
                min = y.min;
            }
            if (y.max > max) {
                max = y.max;
            }
            return new MinMax(min, max);
        });

        return ans;
    }
}