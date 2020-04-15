/open Voem.java
Voem<Integer> vOK, vFail
vOK = Voem.ok(1)
vFail = Voem.fail("error")
vFail
vOK.map(x -> x + 3)
vOK.map(x -> x / 0)
vFail.map(x -> x + 3)
vFail.map(x -> x / 0)
vOK.flatMap(x -> Voem.ok(x + 3))
vOK.flatMap(x -> Voem.fail("out of time"))
vFail.flatMap(x -> Voem.fail("out of time"))
vFail.flatMap(x -> Voem.ok(3));
Function<Object,Integer> f = x -> x.hashCode()
Voem<Number> v = Voem.ok("OK").map(f)
v
Voem<Number> v = Voem.ok(null).map(f)
v
vOK.orElse(5)
vFail.orElse(5)
/exit