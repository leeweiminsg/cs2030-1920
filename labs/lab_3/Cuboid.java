public class Cuboid extends Shape3D {
	protected double height, width, length;

	public Cuboid(double height, double width, double length){
		this.height = height;
		this.width = width;
		this.length = length;
	}
	
	@Override
	public double getVolume() {
		return height * width * length;
	}

	public double getSurfaceArea() {
		return (length * height + width * height + length * width) * 2;
	}

	public Cuboid setHeight(double height) {
		return new Cuboid(height, width, length);
	}

	public Cuboid setWidth(double width) {
		return new Cuboid(height, width, length);
	}

	public Cuboid setLength(double length) {
		return new Cuboid(height, width, length);
	}

	@Override
	public String toString() {
		return String.format("Cuboid [%.2f x %.2f x %.2f]", height, width, length);
	}
}
