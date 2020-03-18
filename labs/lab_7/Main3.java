import java.util.stream.*;

class Main {
  static int[] intArray;

  static long countRepeats(int... array) {
    intArray = array;

    return IntStream.rangeClosed(1, array.length - 1).filter(Main::hasRepeat).count();
  }

  static boolean hasRepeat(int i) {
    return (i == intArray.length - 1) ? intArray[i] == intArray[i - 1]
        : intArray[i] == intArray[i - 1] && intArray[i] != intArray[i + 1];
  }
}