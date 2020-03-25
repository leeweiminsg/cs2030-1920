import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

interface InfiniteList<T> {
    Optional<T> get();

    static <T> InfiniteList<T> generate(Supplier<? extends T> supplier) {
        return InfiniteListImpl.generate(supplier);
    }

    static <T> InfiniteList<T> iterate(T seed, UnaryOperator<T> f) {
        return InfiniteListImpl.iterate(seed, f);
    }

    InfiniteList<T> limit(long n);

    void forEach(Consumer<? super T> action);

    Object[] toArray();

    <R> InfiniteList<R> map(Function<? super T, ? extends R> mapper);

    InfiniteList<T> filter(Predicate<? super T> predicate);

    InfiniteList<T> takeWhile(Predicate<? super T> predicate);

    long count();

    Optional<T> reduce(BinaryOperator<T> accumulator);

    T reduce(T identity, BinaryOperator<T> accumulator);
}