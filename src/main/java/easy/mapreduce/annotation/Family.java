package easy.mapreduce.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Family {

	String value() default "val";
	
	boolean includeAll() default true;
	
	boolean excludeRowkey() default true;
}
