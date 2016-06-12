package easy.mapreduce.element;

import easy.mapreduce.annotation.Family;
import easy.mapreduce.annotation.Table;

/**
 * parse model, always not save this instance
 * @author zhuyuqiang
 * @date 2016年6月3日 下午6:39:41
 * @version 1.0
 * @param <E>
 */
public class ClassSetting<E> {
	
	private final byte[] family;
	private final boolean includeAll;
	private final boolean excludeRowkey;
	private final Class<E> clazz;
	private String tableName;
	private RowkeySetting<E> rowkeySetting;
	
	public ClassSetting(Class<E> clazz) {
		this.clazz = clazz;
		// parse Table
		Table table = clazz.getAnnotation(Table.class);
		if (table != null) {
			this.tableName = table.name();
			this.rowkeySetting = new RowkeySetting<E>(clazz, table.rowkey());
		}
		// parse Family
		Family modelFamily = clazz.getAnnotation(Family.class);
		if (table == null && modelFamily == null) {
			// TODO thiiking about the annotation parsing order!
		}
		// TODO remove below!!!
		includeAll = false;
		excludeRowkey = false;
		family = null;
	}
	

}
