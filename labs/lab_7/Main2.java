import java.util.stream.Stream;

class Main {
    static int gcd(int m, int n) {
        return Stream
                .iterate(new Pair<>(Math.max(m, n), Math.min(m,
                        n)), 
                        pair -> pair.n != 0,
                        p -> new Pair<>(p.n, p.m % p.n))
                .reduce((p1, p2) -> p2)
                .get()
                .n;
    }
}