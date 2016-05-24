package easy.mapreduce.tran;

import java.io.IOException;

public interface Tran<F, T> {

	/**
	 * translate obj, from => to
	 * @param from
	 * @return
	 * @throws Exception
	 */
	public T from(F from) throws IOException;
	
	/**
	 * translate obj, to => from
	 * @param to
	 * @return
	 * @throws Exception
	 */
	public F to(T to) throws IOException;
}
