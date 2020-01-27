class Point {
    private double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Point midPoint(Point p) {
        double new_x = (this.x + p.x) / 2;
        double new_y = (this.y + p.y) / 2;
       
        return new Point(new_x, new_y);
    }

    double angleTo(Point p) {
        double d_x = p.x - this.x;
        double d_y = p.y - this.y;

        return Math.atan2(d_y, d_x);
    }

    double distanceTo(Point otherpoint) {
        double dispX = this.x - otherpoint.x;
        double dispY = this.y - otherpoint.y;

        return Math.sqrt(dispX * dispX + dispY * dispY);
    }

    Point moveTo(double theta, double d) {
        double new_x = this.x + d * Math.cos(theta);
        double new_y = this.y + d * Math.sin(theta);

        return new Point(new_x, new_y);
    }

    @Override
    public String toString() {
        return "point (" + String.format("%.3f", this.x) + ", " + String.format("%.3f", this.y) + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Point) {
            Point p = (Point) obj;
            return Math.abs(this.x - p.x) < 1E-15 &&
            Math.abs(this.y - p.y) < 1E-15;
        } else {
            return false;
        }
    }
}
