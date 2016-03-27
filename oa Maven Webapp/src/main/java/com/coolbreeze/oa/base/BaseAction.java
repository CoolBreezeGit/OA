package com.coolbreeze.oa.base;

import javax.annotation.Resource;

import com.coolbreeze.oa.service.DepartmentService;
import com.coolbreeze.oa.service.ProcessDefService;
import com.coolbreeze.oa.service.ProcessTemplateService;
import com.coolbreeze.oa.service.RoleService;
import com.coolbreeze.oa.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{

	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
	protected ProcessDefService processDefService;
	@Resource
	protected ProcessTemplateService processTemplateService;
}
