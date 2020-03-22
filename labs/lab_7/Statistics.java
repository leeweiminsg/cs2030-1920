class Statistics {
    private final int min;
    private final int max;
    private final int sum;
    private final int count;

    Statistics(int min, int max, int sum, int count) {
        this.min = min;
        this.max = max;
        this.sum = sum;
        this.count = count;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getSum() {
        return sum;
    }

    public int getCount() {
        return count;
    }
}
