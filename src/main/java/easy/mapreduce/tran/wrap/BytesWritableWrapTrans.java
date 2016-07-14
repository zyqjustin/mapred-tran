package easy.mapreduce.tran.wrap;

import org.apache.hadoop.io.BytesWritable;

import easy.mapreduce.tran.Tran;
import easy.mapreduce.tran.impl.BytesWritableTran;

public class BytesWritableWrapTrans {

	public static <E> Tran<E, BytesWritable> wrap(Tran<E, byte[]> bst) {
		return new BytesWritableTran<E>(bst);
	}
	
	public static <E> Tran<E, BytesWritable> tranFor(Class<E> type) {
		return new BytesWritableTran<E>(CompositeBytesTranBuilder.tranFor(type));
	}
}
