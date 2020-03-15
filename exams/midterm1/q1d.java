import java.util.*;

class D<T> {
  public static <T> List<T> add(List<T> Lst, T t) {
    Lst.add(t);

    return Lst;
  }

  public static <T, U extends T> List<T> join(List<T> Lst1, List<U> Lst2) {
    if (Lst1.equals(Lst2)) {
      return Lst1;
    }

    ArrayList<T> Lst3 = new ArrayList<>(Lst1);

    Lst3.addAll(Lst2);

    return Lst3;
  }
}