package easy.mapreduce.exceptions;

/**
 * when encountered translate failed between [fromClass] and [toClass]
 * @author zhuyuqiang
 * @date 2016年6月2日 下午5:34:46
 * @version 1.0
 */
public class TranedRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 6530446239998071580L;
	
	@SuppressWarnings("unused")
	private Class<?> fromClass;
	@SuppressWarnings("unused")
	private Class<?> toClass;
	
	public TranedRuntimeException(Class<?> fromClass, Class<?> toClass) {
		super("Trans failed, from " + fromClass + ", to " + toClass);
		this.fromClass = fromClass;
		this.toClass = toClass;
	}

	public TranedRuntimeException(String message, Class<?> fromClass, Class<?> toClass) {
		super(message);
		this.fromClass = fromClass;
		this.toClass = toClass;
	}

	public TranedRuntimeException(String message, Class<?> fromClass, Class<?> toClass, Throwable cause) {
		super(message, cause);
		this.fromClass = fromClass;
		this.toClass = toClass;
	}

	public Class<?> getFromClass() {
		return fromClass;
	}

	public Class<?> getToClass() {
		return toClass;
	}
	
}
