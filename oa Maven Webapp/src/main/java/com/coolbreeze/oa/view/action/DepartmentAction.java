package com.coolbreeze.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.coolbreeze.oa.base.BaseAction;
import com.coolbreeze.oa.domain.Department;
import com.coolbreeze.oa.service.DepartmentService;
import com.coolbreeze.oa.tool.DepartmentTreeList;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

	private Long parentId;

	// 显示
	public String list() {

		List<Department> departmentList = departmentService.findAll();
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

		List<Department> departmentList = departmentService.findTopList();
		departmentList=DepartmentTreeList.treeList(departmentList, "┠");
		
		ActionContext.getContext().put("departmentList", departmentList);

		return "addUI";
	}

	// 添加
	public String add() {
		//设置上级部门
		modelDTO.setParent(departmentService.getById(parentId));
		departmentService.add(modelDTO);
		
		System.out.println(modelDTO.getName());
		
		return "redirectList";
	}

	// 进入修改页面
	public String editUI() {
		Department department = departmentService.getById(modelDTO.getId());
		
		//回显上级部门
		/*List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);*/
		List<Department> departmentList = departmentService.findTopList();
		departmentList=DepartmentTreeList.treeList(departmentList, "┠");
		
		ActionContext.getContext().put("departmentList", departmentList);
		
		/*
		 * 需要处理当部门没有上级部门时的空指针异常！！
		 */
		if(department.getParent()!=null){
			parentId=department.getParent().getId();
		}
		
		ActionContext.getContext().getValueStack().push(department);
		return "editUI";
	}

	// 修改
	public String edit() {
		Department department = departmentService.getById(modelDTO.getId());
		department.setName(modelDTO.getName());
		department.setDescription(modelDTO.getDescription());
		
		//修改上级部门
		department.setParent(departmentService.getById(parentId));
		
		departmentService.update(department);
		return "redirectList";
	}
	
	
	

	// ===========================================================
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
