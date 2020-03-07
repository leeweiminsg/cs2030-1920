import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.Comparator;

public class ImmutableList<T> {
    private final List<T> list;

    public ImmutableList(List<T> list) {
        this.list = new ArrayList<>(list);
    }

    @SafeVarargs
    public ImmutableList(T... items) {
        ArrayList<T> clonedList = new ArrayList<>(Arrays.asList(items));

        this.list = clonedList;
    }

    public ImmutableList<T> add(T t) {
        ArrayList<T> clonedList = new ArrayList<>(list);

        clonedList.add(t);

        return new ImmutableList<>(clonedList);
    }

    public ImmutableList<T> remove(T t) {
        ArrayList<T> clonedList = new ArrayList<>(list);

        clonedList.remove(t);

        return new ImmutableList<>(clonedList);
    }

    public ImmutableList<T> replace(T t1, T t2) {
        if (t1.equals(t2)) {
            return this;
        }

        ArrayList<T> clonedList = new ArrayList<>(list);

        for (int i = 0; i < clonedList.size(); i++) {
            if (clonedList.get(i).equals(t1)) {
                clonedList.set(i, t2);
            }
        }

        return new ImmutableList<>(clonedList);
    }

    public ImmutableList<T> filter(Predicate<? super T> pred) {
        ArrayList<T> newList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i); 

            if (pred.test(t)) {
                newList.add(t);
            }
        }

        return new ImmutableList<>(newList);
    }

    public <R> ImmutableList<R> map(Function<? super T, ? extends R> func) {
        ArrayList<R> newList = new ArrayList<>();

        for (T item : list) {
            R newItem = func.apply(item);

            newList.add(newItem);
        }

        return new ImmutableList<>(newList);
    }

    public ImmutableList<T> limit(long n) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException("limit size < 0");
        }

        if (n >= list.size()) {
            ArrayList<T> clonedList = new ArrayList<>(list);

            return new ImmutableList<>(clonedList);
        }

        ArrayList<T> newList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            T item = list.get(i);
            newList.add(item);
        }

        return new ImmutableList<>(newList);

    }

    public ImmutableList<T> sorted() throws IllegalStateException {
        ArrayList<T> newList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);

            if (!(item instanceof Comparable)) {
                throw new IllegalStateException("List elements do not implement Comparable");
            }

            newList.add(item);
        }

        newList.sort(null);

        @SuppressWarnings("unchecked")
        ArrayList<T> newArrayList = (ArrayList<T>) newList;
        return new ImmutableList<>(newArrayList);
    }

    public ImmutableList<T> sorted(Comparator<T> c) throws NullPointerException {
        if (c == null) {
            throw new NullPointerException("Comparator is null");
        }

        ArrayList<T> clonedList = new ArrayList<>(list);

        clonedList.sort(c);

        return new ImmutableList<>(clonedList);
    }

    public Object[] toArray() {
        Object[] newArray = new Object[list.size()];

        for (int i = 0; i < list.size(); i++) {
            newArray[i] = (Object) list.get(i);
        }

        return newArray;
    }

    public <T> T[] toArray(T[] arr) throws NullPointerException, ArrayStoreException {
        if (arr == null) {
            throw new NullPointerException("Input array cannot be null");
        }

        try {
            return list.toArray(arr);
        } catch (Exception ex) {
            throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
        }
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
