public class LastDigitsOfHashCode implements Transformer<Object, Integer> {
	private final Integer k;

	LastDigitsOfHashCode(Integer k) {
		this.k = k;
	}

	@Override
	public Integer transform(Object obj) {
		return Math.abs((int) (obj.hashCode() % (Math.pow(10, k))));
	}
}
