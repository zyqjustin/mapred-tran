package easy.mapreduce.hbase;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.io.BytesWritable;
import org.springframework.core.GenericTypeResolver;

import easy.mapreduce.tran.BeanMapper;
import easy.mapreduce.tran.Tran;
import easy.mapreduce.tran.wrap.BytesWritableWrapTrans;

public abstract class ByteTranedTableReducer<KEY, VALUE, MODEL> extends TranedTableReducer<KEY, VALUE, BytesWritable, BytesWritable> {

	private BeanMapper<MODEL> beanMapper;

	public ByteTranedTableReducer(BeanMapper<MODEL> beanMapper) {
		super();
		this.beanMapper = beanMapper;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ByteTranedTableReducer() {
		Class[] typeArguments = GenericTypeResolver.resolveTypeArguments(getClass(), ByteTranedTableReducer.class);
		this.beanMapper = new BeanMapper<MODEL>((Class<MODEL>) typeArguments[2]);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected <F, T> Tran<F, T> getTran(Class<F> fromClass, Class<T> toClass) {
		return (Tran<F, T>) BytesWritableWrapTrans.tranFor(fromClass);
	}
	
	protected Put tranPut(MODEL m, boolean isSkipNull) {
		return beanMapper.tranPut(m, isSkipNull);
	}
	
	protected void write(MODEL m) throws IOException, InterruptedException{
		write(m, true);
	}

	protected void write(MODEL m, boolean isSkipNull) throws IOException, InterruptedException {
		context.write(null, tranPut(m, isSkipNull));
	}
	
	// TODO other require
}
