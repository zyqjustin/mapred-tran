package easy.mapreduce.tran;

import java.io.IOException;

public class EmptyTran<T> implements Tran<T, T> {
	
	private EmptyTran() {}
	
	@SuppressWarnings("unchecked")
	public static <T> Tran<T, T> getInstance() {
		return new EmptyTran<T>();
	}

	@Override
	public T from(T from) throws IOException {
		return from;
	}

	@Override
	public T to(T to) throws IOException {
		return to;
	}

}
