import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Lazy<T> {
    private T t;
    private final Supplier<? extends T> s;
    private boolean memoized;

    private Lazy(T t) {
        this.t = t;
        s = () -> t;
        memoized = true;
    }

    private Lazy(Supplier<? extends T> s) {
        t = null;
        this.s = s;
        memoized = false;
    }

    static <T> Lazy<T> ofNullable(T v) {
        return new Lazy<>(v);
    }

    static <T> Lazy<T> generate(Supplier<? extends T> supplier) {
        return new Lazy<>(supplier);
    }

    <R> Lazy<R> map(Function<? super T, ? extends R> mapper) {
        return Lazy.generate(() -> get().isPresent() ? mapper.apply(t) : null);
    }

    Lazy<T> filter(Predicate<? super T> predicate) {
        if (memoized && t == null) {
            return Lazy.generate(() -> null);
        } else {
            return Lazy.generate(() -> {
                return (get().isPresent() && predicate.test(t)) ? t : null;
            });
        }
    }

    Optional<T> get() {
        if (!memoized) {
            t = s.get();
            memoized = true;
        }

        return t == null ? Optional.empty() : Optional.of(t);
    }

    @Override
    public String toString() {
        if (!memoized) {
            return "?";
        }

        return (t == null) ? "null" : t.toString();
    }
}