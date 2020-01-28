import java.util.Scanner;

class Main {
    private static Point[] pointsArray = null;

    static Circle createCircle(Point p, Point q, double r) {
        if (p.equals(q)) {
            return null;
        }

        Point midPoint = p.midPoint(q);
        double theta = p.angleTo(q) + Math.PI / 2;
        double distance = Math.sqrt(Math.pow(r, 2) - Math.pow(q.distanceTo(midPoint), 2));

        if (Double.isNaN(distance)) {
            return null;
        }

        Point centre = midPoint.moveTo(theta, distance);

        return Circle.getCircle(centre, r);
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int max = 0;

        pointsArray = new Point[n];

        for (int i = 0; i < n; i++) {
            double p_x = sc.nextDouble();
            double p_y = sc.nextDouble();

            Point p = new Point(p_x, p_y);

            pointsArray[i] = p;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Point p = pointsArray[i];
                Point q = pointsArray[j];

                // Unit circle
                if (p.distanceTo(q) <= 2 && p.distanceTo(q) != 0) {
                    Circle circle = Main.createCircle(p, q, 1);

                    int pointsInCircle = 0;

                    for (Point r: pointsArray) {
                        if (circle.contains(r)) {
                            pointsInCircle++;
                        }
                    }

                    max = Math.max(max, pointsInCircle);
                }
            }
        }

        System.out.println("Maximum Disc Coverage: " + max);
    }
}
