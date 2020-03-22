import java.util.List;
import java.util.ArrayList;

class D<T> {
  public static <T> List<T> add(List<T> Lst, T t) {
    Lst.add(t);

    return Lst;
  }

  public static <T> List<T> join(List<T> Lst1, List<? extends T> Lst2) {
    if (Lst1.equals(Lst2)) {
      return Lst1;
    }

    ArrayList<T> Lst3 = new ArrayList<>(Lst1);

    Lst3.addAll(Lst2);

    return Lst3;
  }
}