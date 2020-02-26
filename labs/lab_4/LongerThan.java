public class LongerThan implements BooleanCondition<String> {
	private final int limit;

	LongerThan(int limit) {
		this.limit = limit;
	}

	@Override
	public boolean test(String s) {
		if (s == null) {
			return false;
		}
		
		return s.length() > limit;
	}
}
