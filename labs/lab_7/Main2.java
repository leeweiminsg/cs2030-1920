import java.util.stream.Stream;

class Main {
    static int gcd(int m, int n) {
        return Stream
            .iterate(new Pair<>(Math.max(m, n), Math.min(m, n)), pair -> pair.getSecond() != 0,
                pair -> new Pair<>(pair.getSecond(), pair.getFirst() % pair.getSecond()))
            .reduce((first, second) -> second).get().getSecond();
    }
}
