import java.util.function.Function;
class Voem<T> {
   private T t;
   private final String msg;
   Voem(T t) {
      this.t = t;
      this.msg = null;
   }
    Voem(String msg) {
      this.t = null;
      this.msg = msg;
   }
   static <T> Voem<T> ok(T v) {
      return new Voem(v);
   }
   static <T> Voem<T> fail(String msg) {
      return new Voem(msg);
   }
   <U> Voem<U> map(Function<T, U> f) {
      if (t == null) {
         return this;
      }
      try {
          U newT = f.apply(t);
          return Voem.ok(newT);
      } catch (Exception e) {
         return Voem.fail(e.getMessage());
      }
   }
<U> Voem<U> flatMap(Function<T, Voem<U>> f) {
      if (t == null) {
         return this;
      }
      try {
          return f.apply(t);
      } catch (Exception e) {
         return Voem.fail(e.getMessage());
      }
   }
   T orElse(T t) {
      if (this.t == null) {
         return t;
      }
      return this.t;
   }
   @Override
   public String toString() {
      if (t != null) {
         return "Done: " + t.toString();
      }
      return "Oops: " + msg;
   }
}
