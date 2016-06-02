package easy.mapreduce.hbase;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Reducer;
import org.springframework.core.GenericTypeResolver;

import easy.mapreduce.exceptions.TranedRuntimeException;
import easy.mapreduce.tran.EmptyTran;
import easy.mapreduce.tran.Tran;

/**
 * 
 * @author zhuyuqiang
 * @author sam
 * @date 2016年6月2日 下午3:18:03
 * @version 1.0
 * @param <KEY>
 * @param <VALUE>
 * @param <KEYIN>
 * @param <VALUEIN>
 */
public abstract class TranedTableReducer<KEY, VALUE, KEYIN extends WritableComparable<? super KEYIN>, VALUEIN extends WritableComparable<? super VALUEIN>>
		extends TableReducer<KEYIN, VALUEIN, ImmutableBytesWritable> {

	protected Context context;
	private Tran<KEY, KEYIN> keyTran;
	private Tran<VALUE, VALUEIN> valueTran;
	
	@SuppressWarnings("unchecked")
	public TranedTableReducer() {
		super();
		// get generic class type
		Class<?>[] typeArguments = GenericTypeResolver.resolveTypeArguments(getClass(), TranedTableReducer.class);
		keyTran = initKeyTran((Class<KEY>) typeArguments[0], (Class<KEYIN>) typeArguments[2]);
		valueTran = initValueTran((Class<VALUE>) typeArguments[0], (Class<VALUEIN>) typeArguments[2]);
	}

	/**
	 * should rewrite keytran.
	 * @param fromClass
	 * @param toClass
	 * @return
	 */
	protected Tran<KEY, KEYIN> initKeyTran(Class<KEY> fromClass, Class<KEYIN> toClass) {
		if (fromClass == toClass) {
			return EmptyTran.getInstance();
		}
		return getTran(fromClass, toClass);
	}
	
	/**
	 * should rewrite valuetran.
	 * @param fromClass
	 * @param toClass
	 * @return
	 */
	protected Tran<VALUE, VALUEIN> initValueTran(Class<VALUE> fromClass, Class<VALUEIN> toClass) {
		if (fromClass == toClass) {
			return EmptyTran.getInstance();
		}
		return getTran(fromClass, toClass);
	}

	/**
	 * common tran.
	 * @param fromClass
	 * @param toClass
	 * @return
	 */
	private <F, T> Tran<F, T> getTran(Class<F> fromClass, Class<T> toClass) {
		throw new UnsupportedOperationException("unsupport class tran.");
	}
	
	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException {
		super.cleanup(context);
		this.context = null;
	}
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		super.setup(context);
		this.context = context;
	}
	
	@Override
	protected void reduce(KEYIN keyin, Iterable<VALUEIN> valueinIter, Context context) throws IOException, InterruptedException {
		KEY key = null;
		try {
			key = keyTran.to(keyin);
		} catch (Exception e) {
			// TODO report error data cell
			return;
		}
		
		ValueIterTran valueIterTran = new ValueIterTran(valueinIter);
		try {
			tranedReduce(key, valueIterTran, context);
		} catch (Exception e) {
			// TODO report error data cell
			return;
		}
	}
	
	/**
	 * new reduce method which replace the method method from MapReduce.  
	 * @param key
	 * @param valueIterTran
	 * @param context
	 * @throws IOException
	 * @throws InterruptedException
	 */
	protected abstract void tranedReduce(KEY key, ValueIterTran valueIterTran, Context context) throws IOException, InterruptedException;

	// TODO method for report error data cell
	
	class ValueIterTran implements Iterable<VALUE> {
		Iterator<VALUEIN> vi;
		VALUEIN valuein = null;
		VALUE value = null;
		
		ValueIterTran(Iterable<VALUEIN> valueIterable) {
			this.vi = valueIterable.iterator();
		}

		@Override
		public Iterator<VALUE> iterator() {
			return new Iterator<VALUE>() {

				@Override
				public boolean hasNext() {
					return vi.hasNext();
				}

				@Override
				public void remove() {
					vi.remove();
				}
				
				@Override
				public VALUE next() {
					valuein = vi.next();
					
					try {
						return valueTran.to(valuein);
					} catch (IOException e) {
						throw new TranedRuntimeException(value.getClass(), valuein.getClass());
					}
				}

			};
		}
		
	}
}
