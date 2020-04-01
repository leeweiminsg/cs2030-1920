import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class InfiniteListImpl<T> implements InfiniteList<T> {
    private final Lazy<T> head;
    private final Supplier<InfiniteListImpl<T>> tail;

    InfiniteListImpl() {
        head = null;
        tail = null;
    }

    InfiniteListImpl(T head, Supplier<InfiniteListImpl<T>> tail) {
        this.head = Lazy.ofNullable(head);
        this.tail = tail;
    }

    InfiniteListImpl(Lazy<T> head, Supplier<InfiniteListImpl<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    static <T> InfiniteListImpl<T> generate(Supplier<? extends T> s) {
        Lazy<T> newHead = Lazy.generate(s);
        Supplier<InfiniteListImpl<T>> newTail = () -> InfiniteListImpl.generate(s);

        return new InfiniteListImpl<>(newHead, newTail);
    }

    static <T> InfiniteListImpl<T> iterate(T seed, UnaryOperator<T> next) {
        Lazy<T> newHead = Lazy.ofNullable(seed);
        Supplier<InfiniteListImpl<T>> newTail = 
            () -> InfiniteListImpl.iterate(next.apply(seed), next);

        return new InfiniteListImpl<>(newHead, newTail);
    }

    @Override
    public <R> InfiniteListImpl<R> map(Function<? super T, ? extends R> mapper) {
        Lazy<R> newHead = head.map(mapper);
        Supplier<InfiniteListImpl<R>> newTail = () -> tail.get().map(mapper);

        return new InfiniteListImpl<R>(newHead, newTail);
    }

    @Override
    public InfiniteListImpl<T> filter(Predicate<? super T> predicate) {
        Lazy<T> newHead = head.filter(predicate);
        Supplier<InfiniteListImpl<T>> newTail = () -> tail.get().filter(predicate);

        return new InfiniteListImpl<>(newHead, newTail);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        InfiniteListImpl<T> list = this;

        while (!list.isEmpty()) {
            list.head.get().ifPresent(action);

            list = list.tail.get();
        }
    }

    @Override
    public Object[] toArray() {
        ArrayList<Object> array = new ArrayList<>();
        InfiniteListImpl<T> list = this;

        while (!list.isEmpty()) {
            list.head.get().ifPresent(t -> array.add(t));

            list = list.tail.get();
        }

        return array.toArray();
    }

    @Override
    public InfiniteListImpl<T> limit(long n) {
        if (n <= 0) {
            return new EmptyList<>();
        } else if (this.isEmpty()) {
            return this;
        } else if (n == 1) {
            Supplier<InfiniteListImpl<T>> newTail = 
                () -> head.get().isPresent() 
                        ? new EmptyList<>()
                        : tail.get().limit(n);

            return new InfiniteListImpl<>(head, newTail);
        } else {
            Supplier<InfiniteListImpl<T>> newTail = 
                () -> head.get().isPresent() 
                        ? tail.get().limit(n - 1)
                        : tail.get().limit(n);

            return new InfiniteListImpl<>(head, newTail);
        }
    }

    @Override
    public long count() {
        long n = 0;
        InfiniteListImpl<T> list = this;

        while (!list.isEmpty()) {
            if (list.head.get().isPresent()) {
                n += 1;
            }

            list = list.tail.get();
        }

        return n;
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator) {
        U result = identity;
        InfiniteListImpl<T> list = this;

        while (!list.isEmpty()) {
            if (list.head.get().isPresent()) {
                result = accumulator.apply(result, list.head.get().get());
            }

            list = list.tail.get();
        }

        return result;
    }

    @Override
    public InfiniteListImpl<T> takeWhile(Predicate<? super T> predicate) {
        if (this.isEmpty()) {
            return this;
        } else {
            Lazy<T> newHead = head.filter(predicate);

            Supplier<InfiniteListImpl<T>> newTail = 
                () -> head.get().isPresent() && newHead.get().isEmpty() 
                        ? new EmptyList<>() 
                        : tail.get().takeWhile(predicate);

            return new InfiniteListImpl<>(newHead, newTail);
        }
    }

    @Override
    public InfiniteList<T> peek() {
        head.get().ifPresent(System.out::println);

        return tail.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}