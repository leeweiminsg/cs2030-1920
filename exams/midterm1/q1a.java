class X {
    private final int n;

    X(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return String.format("X:%d", n);
    }
}

class Y {
    private final X x;

    Y(X x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return String.format("Y->" + x.toString());
    }
}