package com.coolbreeze.oa.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ModelDriven;

public class ModelDrivenBaseAction<T> extends BaseAction implements ModelDriven<T> {

	protected T modelDTO;

	// 通过反射生成modelDTO实例
	public ModelDrivenBaseAction() {
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
		try {
			modelDTO = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return modelDTO;
	}

}
