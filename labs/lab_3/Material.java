public class Material {
	protected String name;
	protected double density;

	public Material(String name, double density) {
		this.name = name;
		this.density = density;
	}

	public String getName() {
		return name;
	}

	public double getDensity() {
		return density;
	}
}
