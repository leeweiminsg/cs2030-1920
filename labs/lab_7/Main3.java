import java.util.stream.IntStream;

class Main {
    static long countRepeats(int... array) {
        return IntStream.rangeClosed(1, array.length - 1)
            .filter(
                i -> 
                    (i == array.length - 1) 
                    ? array[i] == array[i - 1] 
                    : array[i] == array[i - 1] && array[i] != array[i + 1]).count();
    }
}
