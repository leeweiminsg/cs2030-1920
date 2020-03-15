/open q1c.java
B b
C c
c = b
b = c
new B()
new C()
new B().add(new B())
new B().add(new C())
new B().add(new C().add(new B()))
new B().add(new C().add(new C()))
new C().add(new C().add(new B())).add(new C())
/exit