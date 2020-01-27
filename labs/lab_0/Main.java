import java.util.Scanner;

class Main {
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
        double p_x = sc.nextDouble();
        double p_y = sc.nextDouble();
        double q_x = sc.nextDouble();
        double q_y = sc.nextDouble();
        double r = sc.nextDouble();

        Point p = new Point(p_x, p_y);
        Point q = new Point(q_x, q_y);
        
        Circle circle = Main.createCircle(p, q, r);

        if (circle == null) {
            System.out.println("No valid circle can be created");
        } else {
            System.out.println("Created: " + circle.toString());
        }
    }
}
