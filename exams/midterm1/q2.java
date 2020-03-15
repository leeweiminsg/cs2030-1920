import java.util.*;

interface TypeCaster<S, T> {
  T cast(S s);
}

class ToString<S> implements TypeCaster<S, String> {
  @Override
  public String cast(S s) {
    return s.toString();
  }}

  TypeCaster<S, String> ToString = s -> s.toString();

  class Round implements TypeCaster<Double, Integer> {
    @Override
    public Integer cast(Double d) {
      return (int) Math.round(d);
    }
  }

  TypeCaster<Double, Integer> Round = d -> (int) Math.round(d);

  class ToList<T> implements TypeCaster<T[], List<T>> {
    @Override
    public List<T> cast(T[] tArr) {
      return Arrays.asList(tArr);
    }
  }

class ListCaster {
  static <S, T> List<T> castList(List<S> Lst, TypeCaster<? super S, ? extends T> tc) {
    List<T> TLst = new ArrayList<T>();

    Lst.forEach(s -> TLst.add(tc.cast(s)));

    return TLst;
  }
}