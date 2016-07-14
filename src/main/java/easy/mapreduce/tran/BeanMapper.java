package easy.mapreduce.tran;

import org.apache.hadoop.hbase.client.Put;

// TODO
public class BeanMapper<M> {

	public BeanMapper(Class<M> clazz) {
		
	}
	
	
	public Put tranPut(M m, boolean isSkipNull) {
		// TODO 
		return null;
	}
}
