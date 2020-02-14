public class SolidShape3D {
	protected Shape3D shape;
	protected Material material;

	public SolidShape3D(Shape3D shape, Material material) {
		this.shape = shape;
		this.material = material;
	}

	public double getDensity() {
		return material.getDensity();
	}

	public double getMass() {
		return shape.getVolume() * getDensity();
	}

	@Override
	public String toString() {
		return String.format("Solid" + shape.toString() + " with a mass of %.2f", getMass());
	}
}
