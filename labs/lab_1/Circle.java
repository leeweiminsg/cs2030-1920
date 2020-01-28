class Circle {
    private Point centre;
    private double radius;

    private Circle(Point p, double r) {
        this.centre = p;
        this.radius = r;
    }

    static Circle getCircle(Point p, double r) {
        if (r <= 0) {
            return null;
        }

        return new Circle(p, r);
    }

    boolean contains(Point p) {
        return centre.distanceTo(p) <= radius;
    }

    @Override
    public String toString() {
        return "circle of radius " + this.radius + " centered at " + centre.toString();
    }
}
