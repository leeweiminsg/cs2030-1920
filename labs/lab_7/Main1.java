import java.util.stream.*;

class Main {
  public static int[] twinPrimes(int n) {
    return IntStream.rangeClosed(2, n).filter(Main::isPrime).filter(Main::twinPresent).toArray();
  }

  static boolean isPrime(int n) {
    if (n == 0) {
      return false;
    }

    return IntStream.range(2, n).noneMatch(x -> n % x == 0);
  }

  static boolean twinPresent(int n) {
    return IntStream.rangeClosed(n - 2, n + 2).filter(Main::isPrime).anyMatch(x -> n + 2 == x || n - 2 == x);
  }
}