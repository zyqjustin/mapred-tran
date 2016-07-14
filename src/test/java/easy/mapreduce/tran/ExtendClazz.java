package easy.mapreduce.tran;

import org.springframework.core.GenericTypeResolver;

public abstract class ExtendClazz<K, V, M> extends AbstractClazz<K, V, M> {

	public ExtendClazz() {
		super();
		Class[] typeArguments = GenericTypeResolver.resolveTypeArguments(getClass(), ExtendClazz.class);
		for (Class clazz : typeArguments) {
			System.out.println(clazz);
		}
		new ClassSetting(GenericTypeResolver.resolveTypeArguments(getClass(), ExtendClazz.class));
		
	}
	
	
}

class ClassSetting<E> {
	
	private Class<E> cls;

	public ClassSetting(Class<E> cls) {
		super();
		this.cls = cls;
		System.out.println("ClassSetting: " + cls);
	}
	
}