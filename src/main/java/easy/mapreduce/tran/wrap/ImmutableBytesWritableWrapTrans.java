package easy.mapreduce.tran.wrap;

import org.apache.hadoop.hbase.io.ImmutableBytesWritable;

import easy.mapreduce.tran.Tran;
import easy.mapreduce.tran.impl.ImmutableBytesWritableTran;

public class ImmutableBytesWritableWrapTrans {

	public static <E> Tran<E, ImmutableBytesWritable> wrap(Tran<E, byte[]> bst) {
		return new ImmutableBytesWritableTran<E>(bst);
	}
	
	public static <E> Tran<E, ImmutableBytesWritable> tranFor(Class<E> type) {
		return new ImmutableBytesWritableTran<E>(CompositeBytesTranBuilder.tranFor(type));
	}
}
