import java.util.stream.IntStream;

class Main {
    public static int[] twinPrimes(int n) {
        return IntStream
                .rangeClosed(2, n)
                .filter(i -> isPrime(i) && (isPrime(i - 2) || isPrime(i + 2)))
                .toArray();
    }

    static boolean isPrime(int n) {
        if (n == 0) {
            return false;
        }

        return IntStream
                .range(2, n)
                .noneMatch(x -> n % x == 0);
    }
}
