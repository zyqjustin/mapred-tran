package easy.mapreduce.element;

import java.lang.reflect.Field;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author zhuyuqiang
 * @date 2016年6月3日 下午6:43:35
 * @version 1.0
 * @param <E>
 */
public class RowkeySetting<E> {
	private static Logger _logger = LoggerFactory.getLogger(RowkeySetting.class);
	
	private Set<Field> fields;

	/**
	 * 
	 */
	public RowkeySetting(Class<E> clazz, String pattern) {
		// TODO parse rowkey pattern
	}
	
	
}
