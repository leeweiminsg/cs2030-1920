import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class EmptyList<T> extends InfiniteListImpl<T> {
    EmptyList() {
        super(Lazy.ofNullable(null), () -> new EmptyList<>());
    }

    static <T> InfiniteListImpl<T> generate(Supplier<? extends T> s) {
        return new EmptyList<>();
    }

    static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> next) {
        return new EmptyList<>();
    }

    @Override
    public <R> InfiniteListImpl<R> map(Function<? super T, ? extends R> mapper) {
        return new EmptyList<>();
    }

    @Override
    public InfiniteListImpl<T> filter(Predicate<? super T> predicate) {
        return new EmptyList<>();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}