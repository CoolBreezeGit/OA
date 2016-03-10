package com.coolbreeze.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.coolbreeze.oa.service.DepartmentService;
import com.coolbreeze.oa.service.RoleService;
import com.coolbreeze.oa.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	protected T modelDTO;

	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;

	// 通过反射生成modelDTO实例
	public BaseAction() {
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
