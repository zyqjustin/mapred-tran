package easy.mapreduce.hbase;

import java.io.IOException;

import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.WritableComparable;
import org.springframework.core.GenericTypeResolver;

import easy.mapreduce.tran.EmptyTran;
import easy.mapreduce.tran.Tran;

/**
 * 
 * @author zhuyuqiang
 * @author sam
 * @date 2016年5月30日 下午3:18:03
 * @version 1.0
 * @param <KEY>
 * @param <VALUE>
 * @param <KEYOUT>
 * @param <VALUEOUT>
 */
public abstract class TranedTableMapper<KEY, VALUE, KEYOUT extends WritableComparable<? super KEYOUT>, VALUEOUT extends WritableComparable<? super VALUEOUT>> extends TableMapper<KEYOUT, VALUEOUT>{

	protected Context context;
	private Tran<KEY, KEYOUT> keyTran;
	private Tran<VALUE, VALUEOUT> valueTran;
	
	@SuppressWarnings("unchecked")
	public TranedTableMapper() {
		super();
		// get generic class type
		Class<?>[] typeArguments = GenericTypeResolver.resolveTypeArguments(getClass(), TranedTableMapper.class);
		keyTran = initKeyTran((Class<KEY>)typeArguments[0], (Class<KEYOUT>)typeArguments[2]);
		valueTran = initValueTran((Class<VALUE>)typeArguments[1], (Class<VALUEOUT>)typeArguments[3]);
	}

	/**
	 * should rewrite keytran.
	 * @param fromClass
	 * @param toClass
	 * @return
	 */
	protected Tran<KEY, KEYOUT> initKeyTran(Class<KEY> fromClass, Class<KEYOUT> toClass) {
		// should judge first
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
	protected Tran<VALUE, VALUEOUT> initValueTran(Class<VALUE> fromClass, Class<VALUEOUT> toClass) {
		// should judge first
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
	protected <F, T> Tran<F, T> getTran(Class<F> fromClass, Class<T> toClass) {
		throw new UnsupportedOperationException("unsupport class tran.");
	}
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		super.setup(context);
		this.context = context;
	}
	
	@Override
	protected void cleanup(Context context) throws IOException, InterruptedException {
		super.cleanup(context);
		this.context = null;
	}
	
	protected void write(KEY key, VALUE value) throws IOException, InterruptedException {
		KEYOUT keyOut = null;
		VALUEOUT valueOut = null;
		try {
			keyOut = keyTran.from(key);
			valueOut = valueTran.from(value);
		} catch (IOException e) {
			// report error data cell
			return;
		}
		context.write(keyOut, valueOut);
	}
	
	// TODO method for report error data cell
}

