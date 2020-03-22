import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

interface TypeCaster<S, T> {
  T cast(S s);
}

class ToString<S> implements TypeCaster<S, String> {
  @Override
  public String cast(S s) {
    return s.toString();
  }
}

class Round implements TypeCaster<Double, Integer> {
  @Override
  public Integer cast(Double d) {
    return (int) Math.round(d);
  }
}

class ToList<T> implements TypeCaster<T[], List<T>> {
  @Override
  public List<T> cast(T[] tArr) {
    return Arrays.asList(tArr);
  }
}

class ListCaster {
  static <S, T> List<T> castList(List<S> lst, TypeCaster<? super S, ? extends T> tc) {
    List<T> Tlst = new ArrayList<>();

    lst.forEach(s -> Tlst.add(tc.cast(s)));

    return Tlst;
  }
}