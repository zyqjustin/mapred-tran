package easy.mapreduce.tran.wrap;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.core.GenericTypeResolver;

import easy.mapreduce.tran.Tran;

public class PrimitiveImmutableBytesWrapTrans {

	private static final Map<Class<?>, Tran<?, ImmutableBytesWritable>> trans = new HashMap<Class<?>, Tran<?, ImmutableBytesWritable>>();

	@SuppressWarnings("unchecked")
	public static <E> Tran<E, ImmutableBytesWritable> getTran(Class<E> clazz) {
		return (Tran<E, ImmutableBytesWritable>) trans.get(clazz);
	}
	
	static <E> void registerTran(Class<E> clazz, Tran<E, ImmutableBytesWritable> tran) {
		trans.put(clazz, tran);
	}
	
	// init 
	public static final Tran<Long, ImmutableBytesWritable> LONG = new ImmutableBytesWritableTran<Long>() {
		@Override
		protected byte[] fromBytes(Long from) {
			return Bytes.toBytes(from);
		}
		@Override
		protected Long toBytes(byte[] bs, int offset, int length) {
			return Bytes.toLong(bs, offset, length);
		}
	};
	
	public static final Tran<Integer, ImmutableBytesWritable> INT = new ImmutableBytesWritableTran<Integer>() {
		@Override
		protected byte[] fromBytes(Integer from) {
			return Bytes.toBytes(from);
		}
		@Override
		protected Integer toBytes(byte[] bs, int offset, int length) {
			return Bytes.toInt(bs, offset, length);
		}
	};
	
	public static final Tran<Short, ImmutableBytesWritable> SHORT = new ImmutableBytesWritableTran<Short>() {
		@Override
		protected byte[] fromBytes(Short from) {
			return Bytes.toBytes(from);
		}
		@Override
		protected Short toBytes(byte[] bs, int offset, int length) {
			return Bytes.toShort(bs, offset, length);
		}
	};
	
	public static final Tran<Float, ImmutableBytesWritable> FLOAT = new ImmutableBytesWritableTran<Float>() {
		@Override
		protected byte[] fromBytes(Float from) {
			return Bytes.toBytes(from);
		}
		@Override
		protected Float toBytes(byte[] bs, int offset, int length) {
			return Bytes.toFloat(bs, offset);
		}
	};
	
	public static final Tran<Double, ImmutableBytesWritable> DOUBLE = new ImmutableBytesWritableTran<Double>() {
		@Override
		protected byte[] fromBytes(Double from) {
			return Bytes.toBytes(from);
		}
		@Override
		protected Double toBytes(byte[] bs, int offset, int length) {
			return Bytes.toDouble(bs, offset);
		}
	};
	
	public static final Tran<Byte, ImmutableBytesWritable> BYTE = new ImmutableBytesWritableTran<Byte>() {
		@Override
		protected byte[] fromBytes(Byte from) {
			return new byte[] { from };
		}
		@Override
		protected Byte toBytes(byte[] bs, int offset, int length) {
			return bs[offset];
		}
	};
	
	public static final Tran<Boolean, ImmutableBytesWritable> BOOLEAN = new ImmutableBytesWritableTran<Boolean>() {
		@Override
		protected byte[] fromBytes(Boolean from) {
			return Bytes.toBytes(from);
		}
		@Override
		protected Boolean toBytes(byte[] bs, int offset, int length) {
			return bs[offset] != (byte) 0;
		}
	};
	
	public static final Tran<Character, ImmutableBytesWritable> CHAR = new ImmutableBytesWritableTran<Character>() {
		@Override
		protected byte[] fromBytes(Character from) {
			return Bytes.toBytes(from);
		}
		@Override
		protected Character toBytes(byte[] bs, int offset, int length) {
			return (char) Bytes.toInt(bs, offset, length);
		}
	};
	
	public static final Tran<String, ImmutableBytesWritable> STRING = new ImmutableBytesWritableTran<String>() {
		@Override
		protected byte[] fromBytes(String from) {
			return Bytes.toBytes(from);
		}
		@Override
		protected String toBytes(byte[] bs, int offset, int length) {
			return Bytes.toString(bs, offset, length);
		}
	};
	
}

abstract class ImmutableBytesWritableTran<E> implements Tran<E, ImmutableBytesWritable> {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ImmutableBytesWritableTran() {
		super();
		Class[] tc = GenericTypeResolver.resolveTypeArguments(getClass(), ImmutableBytesWritableTran.class);
		PrimitiveImmutableBytesWrapTrans.registerTran(tc[0], this);
	}
	
	@Override
	public ImmutableBytesWritable from(E from) throws IOException {
		return new ImmutableBytesWritable(fromBytes(from));
	}

	@Override
	public E to(ImmutableBytesWritable to) throws IOException {
		return toBytes(to.get(), to.getOffset(), to.getLength());
	}

	protected abstract byte[] fromBytes(E from);

	protected abstract E toBytes(byte[] bs, int offset, int length);
}

