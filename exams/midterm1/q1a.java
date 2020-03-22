class X {
    private int n;

    X(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return "X:" + n;
    }
}

class Y {
    private X x;

    Y(X x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Y->" + x.toString();
    }
}