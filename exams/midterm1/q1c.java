abstract class X {
  private String s;

  X(String s) {
    this.s = s;
  }

  @Override
  public String toString() {
    return s;
  }

  public X add(X x) {
    s += x.toString();
    return this;
  }
}

class B extends X {
  B() {
    super("B");
  }
}

class C extends X {
  C() {
    super("C");
  }
}