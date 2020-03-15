/open q1d.java
class E { public String toString() { return "E"; }}
class F extends E { public String toString() { return "F"; }}
List<E> p = D.add(new ArrayList<E>(), new E())
List<F> q = D.add(new LinkedList<F>(), new F())
List<E> r = D.add(D.add(new LinkedList<E>(), new E()), new F())
List<F> s = D.add(D.add(new ArrayList<F>(), new F()), new F())
/var
List<E> x = D.join(p, q)
List<E> y = D.join(p, p)
List<F> z = D.join(q, q)
/var
D.join(q, p)
/exit