public class Box<T> {
	private final T t;
	private static final Box<?> EMPTY_BOX = new Box<>(null);

	private Box(T t) {
		this.t = t;
	}

	public static <T> Box<T> ofNullable(T t) {
		if (t == null) {
			return empty();
		}

		return Box.of(t);
	}

	@SuppressWarnings("unchecked")
	public static <T> Box<T> empty() {
		return (Box<T>) EMPTY_BOX;
	}


	public static <T> Box<T> of(T t) {
		if (t == null) {
			return null;
		}

		return new Box<>(t);
	}

	public boolean isPresent() {
		return t != null;
	}

	T get() {
		return t;
	}

	public Box<T> filter(BooleanCondition<? super T> condition){

		if (!condition.test(t)) {
			return empty();
		}
		return this;
	}

	public <U> Box<U> map(Transformer<? super T, ? extends U> transformer) {
		if(!isPresent()) {
			return empty();
		}

		return new Box<U>(transformer.transform(t));
	}

	@Override
	@SuppressWarnings("unchecked")	
	public boolean equals(Object obj) {
		if (obj instanceof Box<?>) {
			Box<T> box = (Box<T>) obj;

			if (t == null) {
				return box.get() == null;
			}

			return t.equals(box.get());
		}

		return false;
	}

	@Override
	public String toString() {
		if (t == null) {
			return "[]";
		}

		return "[" + t + "]";
	}
}
