package easy.mapreduce.hbase;

import org.apache.hadoop.io.BytesWritable;
import org.springframework.core.GenericTypeResolver;

import easy.mapreduce.tran.ModelBeanTran;

public class ByteTranedTableMapper<MODEL, KEY, VALUE> extends TranedTableMapper<KEY, VALUE, BytesWritable, BytesWritable> {

	private ModelBeanTran<MODEL> modelTran;

	public ByteTranedTableMapper() {
		
		Class<?>[] typeArguments = GenericTypeResolver.resolveTypeArguments(getClass(), ByteTranedTableMapper.class);
		modelTran = new ModelBeanTran<MODEL>((Class<MODEL>) typeArguments[0]);
	}
	
	
}
