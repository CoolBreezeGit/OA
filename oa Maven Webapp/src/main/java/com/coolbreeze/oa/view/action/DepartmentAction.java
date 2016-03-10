package com.coolbreeze.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.coolbreeze.oa.base.BaseAction;
import com.coolbreeze.oa.domain.Department;
import com.coolbreeze.oa.service.DepartmentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department>{

	// 显示
	public String list() {	
		
		List<Department> departmentList=departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		
		return "list";
	}

	// 删除
	public String delete() {
		
		departmentService.delete(modelDTO.getId());
		
		return "redirectList";
	}

	// 进入添加页面
	public String addUI() {		
		return "addUI";
	}

	// 添加
	public String add() {
		System.out.println(modelDTO.getName());
		departmentService.add(modelDTO);
		return "redirectList";
	}

	// 进入修改页面
	public String editUI() {
		Department department=departmentService.getById(modelDTO.getId());
		ActionContext.getContext().getValueStack().push(department);
		return "editUI";
	}

	// 修改
	public String edit() {
		//System.out.println(modelDTO.getId());
		Department department=departmentService.getById(modelDTO.getId());
		department.setName(modelDTO.getName());
		department.setDescription(modelDTO.getDescription());
		departmentService.update(department);
		return "redirectList";
	}
	
}
