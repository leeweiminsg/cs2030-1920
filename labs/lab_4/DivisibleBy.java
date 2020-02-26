public class DivisibleBy implements BooleanCondition<Integer> {
	private final int n;
	
	DivisibleBy(int n) {
		this.n = n;
	}
	
	@Override
	public boolean test(Integer t) {
		if (t == null) {
			return false;
		}

		return t % n == 0;
	}
}
