import java.util.stream.*;
import java.util.List;
import java.util.stream.Collectors.*;
import java.util.NoSuchElementException;

class Main {
  static int max, min;

  public static double normalizedMean(Stream<Integer> stream) {
    List<Integer> listStream = stream.collect(Collectors.toList());

    try {
      max = listStream.stream().mapToInt(Integer::intValue).max().getAsInt();
      min = listStream.stream().mapToInt(Integer::intValue).min().getAsInt();
      double result = (double) (listStream.stream().mapToInt(Integer::intValue).sum() / listStream.stream().count()
          - min) / (max - min);

      return Double.isNaN(result) ? 0 : result;

    } catch (ArithmeticException | NoSuchElementException e) {
      return 0;
    }
  }
}