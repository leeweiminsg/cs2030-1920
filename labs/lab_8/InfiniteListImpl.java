import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

abstract class InfiniteListImpl<T> implements InfiniteList<T> {
    static <T> InfiniteList<T> generate(Supplier<? extends T> supplier) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                return Optional.of(supplier.get());
            }
        };
    }

    static <T> InfiniteList<T> iterate(T seed, UnaryOperator<T> f) {
        return new InfiniteListImpl<T>() {
            private T element = seed;
            private boolean firstElement = true;

            public Optional<T> get() {
                if (firstElement) {
                    firstElement = false;
                } else {
                    element = f.apply(element);
                }

                return Optional.of(element);
            }
        };
    }

    public InfiniteList<T> limit(long n) throws IllegalArgumentException {
        return new InfiniteListImpl<T>() {
            long i = 1;

            @Override
            public Optional<T> get() {
                if (i > n) {
                    return Optional.empty();
                }

                i++;
                return InfiniteListImpl.this.get();
            }
        };
    }

    public void forEach(Consumer<? super T> action) {
        Optional<T> curr = get();

        while (curr.isPresent()) {
            action.accept(curr.get());
            curr = get();
        }
    }

    public Object[] toArray() {
        ArrayList<T> temp = new ArrayList<T>();
        forEach(x -> temp.add(x));

        return temp.toArray();
    }

    public <R> InfiniteList<R> map(Function<? super T, ? extends R> mapper) {
        return new InfiniteListImpl<R>() {
            public Optional<R> get() {
                return InfiniteListImpl.this.get().map(mapper);
            }
        };
    }

    public InfiniteList<T> filter(Predicate<? super T> predicate) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                Optional<T> curr = InfiniteListImpl.this.get();

                while (curr.isPresent() && !predicate.test(curr.get())) {
                    curr = InfiniteListImpl.this.get();
                }

                return curr;
            }
        };
    }

    public InfiniteList<T> takeWhile(Predicate<? super T> predicate) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                Optional<T> curr = InfiniteListImpl.this.get();

                return curr.filter(predicate);
            }
        };
    }

    public long count() {
        long n = 0;

        Optional<T> curr = get();

        while (curr.isPresent()) {
            n++;
            curr = get();
        }

        return n;
    }

    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        Optional<T> curr = get();

        if (curr.isEmpty()) {
            return curr;
        }

        return Optional.of(reduce(curr.get(), accumulator));
    }

    public T reduce(T identity, BinaryOperator<T> accumulator) {
        Optional<T> curr = get();
        T res = identity;

        while (curr.isPresent()) {
            res = accumulator.apply(res, curr.get());
            curr = get();
        }

        return res;
    }
}