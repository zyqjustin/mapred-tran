package easy.mapreduce.tran;

import easy.mapreduce.element.ClassSetting;

public class ModelBeanTran<MODEL> {

	public ModelBeanTran(Class<MODEL> clazz) {
		this(new ClassSetting<MODEL>(clazz));
	}

	ModelBeanTran(ClassSetting<MODEL> classSetting) {
		// TODO init
	}
}
