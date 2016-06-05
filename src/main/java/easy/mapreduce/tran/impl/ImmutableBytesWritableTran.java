package easy.mapreduce.tran.impl;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.hbase.io.ImmutableBytesWritable;

import easy.mapreduce.tran.Tran;

public class ImmutableBytesWritableTran<F> implements Tran<F, ImmutableBytesWritable> {

	private Tran<F, byte[]> traner;
	
	public ImmutableBytesWritableTran(Tran<F, byte[]> traner) {
		super();
		this.traner = traner;
	}

	@Override
	public ImmutableBytesWritable from(F from) throws IOException {
		return new ImmutableBytesWritable(traner.from(from));
	}

	@Override
	public F to(ImmutableBytesWritable to) throws IOException {
		byte[] bs = to.get();
		int offset = to.getOffset();
		int length = to.getLength();
		if (offset == 0 && bs.length == length) {
			return traner.to(bs);
		} else {
			return traner.to(Arrays.copyOfRange(bs, offset, offset + length));
		}
	}

}
