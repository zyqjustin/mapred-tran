package easy.mapreduce.hbase;

import java.io.IOException;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.io.BytesWritable;
import org.springframework.core.GenericTypeResolver;

import easy.mapreduce.tran.ModelBeanTran;
import easy.mapreduce.tran.Tran;
import easy.mapreduce.tran.wrap.BytesWritableWrapTrans;

public abstract class ByteTranedTableMapper<MODEL, KEY, VALUE> extends TranedTableMapper<KEY, VALUE, BytesWritable, BytesWritable> {

	private ModelBeanTran<MODEL> modelTran;

	@SuppressWarnings("unchecked")
	public ByteTranedTableMapper() {
		
		Class<?>[] typeArguments = GenericTypeResolver.resolveTypeArguments(getClass(), ByteTranedTableMapper.class);
		modelTran = new ModelBeanTran<MODEL>((Class<MODEL>) typeArguments[0]);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected <F, T> Tran<F, T> getTran(Class<F> fromClass, Class<T> toClass) {
		return (Tran<F, T>)BytesWritableWrapTrans.tranFor(fromClass);
	}
	
	@Override
	protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
		MODEL m = null;
		try {
			m = modelTran.to(value);
		} catch (IOException e) {
			//TODO report tran error.
			return;
		}
		
		try {
			map(key, m, context);
		} catch (RuntimeException e) {
			//TODO report tran error.
		}
	}

	abstract protected void map(ImmutableBytesWritable key, MODEL m, Context context) throws IOException, InterruptedException;
}
