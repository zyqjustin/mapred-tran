package easy.mapreduce.tran;

import org.apache.hadoop.hbase.client.Result;

import easy.mapreduce.element.ClassSetting;
import easy.mapreduce.element.FamilyMapper;
import easy.mapreduce.element.ResultFieldMeta;

public class ModelBeanTran<MODEL> extends AbstractBeanTran<MODEL, Result, ResultFieldMeta<MODEL>> implements FamilyMapper<MODEL> {

	public ModelBeanTran(Class<MODEL> clazz) {
		this(new ClassSetting<MODEL>(clazz));
	}

	ModelBeanTran(ClassSetting<MODEL> classSetting) {
		// TODO init
	}
}
