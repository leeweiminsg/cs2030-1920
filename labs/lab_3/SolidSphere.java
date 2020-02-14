public class SolidSphere extends Sphere {
	protected double density;

	public SolidSphere(double radius, double density) {
		super(radius);
		this.density = density;
	}

	public double getDensity() {
		return density;
	}

	public double getMass() {
		return getVolume() * getDensity();
	}

	public SolidSphere setRadius(double radius) {
		return new SolidSphere(radius, density);
	}

	@Override
	public String toString() {
		return String.format("SolidSphere [%.2f] with a mass of %.2f", radius, getMass());
	}
}

