public class Sphere extends Shape3D {
	protected double radius;

	public Sphere(double radius) {
		this.radius = radius;
	}

	public Sphere setRadius(double radius) {
		return new Sphere(radius);
	}

	@Override
	public double getVolume() {
		return 4.0/3 * Math.PI * Math.pow(radius, 3);
	}

	public double getSurfaceArea() {
		return 4 * Math.PI * Math.pow(radius, 2);
	}

	@Override
	public String toString() {
		return String.format("Sphere [%.2f]", radius);
	}
}

