public class BoxIt<T> implements Transformer<T, Box<T>>{
	@Override
	public Box<T> transform(T t) {
		return Box.of(t); 
	}
}
