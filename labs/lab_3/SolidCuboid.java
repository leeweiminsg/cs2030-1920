public class SolidCuboid extends Cuboid {
	protected double density;

	public SolidCuboid(double height, double width, double length, double density) {
		super(height, width, length);
		this.density = density;
	}

	public double getDensity() {
		return this.density;
	}

	public double getMass() {
		return super.getVolume() * this.density;
	}

	public SolidCuboid setHeight(double height) {
		return new SolidCuboid(height, width, length, density);
	}

	public SolidCuboid setWidth(double width) {
		return new SolidCuboid(height, width, length, density);
	}

	public SolidCuboid setLength(double length) {
		return new SolidCuboid(height, width, length, density);
	}
	
	@Override
	public String toString() {
		return String.format("SolidCuboid [%.2f x %.2f x %.2f] with a mass of %.2f", height, width, length, this.getMass());
	}
}       
