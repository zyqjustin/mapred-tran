package easy.mapreduce.tran.impl;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.BytesWritable;

import easy.mapreduce.tran.Tran;

public class BytesWritableTran<F> implements Tran<F, BytesWritable>{

	private Tran<F, byte[]> traner;
	
	public BytesWritableTran(Tran<F, byte[]> traner) {
		super();
		this.traner = traner;
	}

	@Override
	public BytesWritable from(F from) throws IOException {
		return new BytesWritable(traner.from(from));
	}

	@Override
	public F to(BytesWritable to) throws IOException {
		byte[] bs = to.getBytes();
		int length = to.getLength();
		if (bs.length == length) {
			return traner.to(bs);
		} else {
			return traner.to(Arrays.copyOfRange(bs, 0, length));
		}
	}

}
