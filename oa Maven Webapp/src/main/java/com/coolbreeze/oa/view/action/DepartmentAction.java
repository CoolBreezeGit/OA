package com.coolbreeze.oa.view.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.coolbreeze.oa.base.BaseAction;
import com.coolbreeze.oa.domain.Department;
import com.coolbreeze.oa.service.DepartmentService;
import com.coolbreeze.oa.tool.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

	private Long parentId;

	// 显示
	public String list() {

		List<Department> departmentList = null;

		if (parentId == null) {
			departmentList = departmentService.findTopList();
		} else {
			departmentList = departmentService.findChildrenList(parentId);
		}

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
		departmentList = DepartmentUtils.treeList(departmentList, "┠");

		ActionContext.getContext().put("departmentList", departmentList);

		return "addUI";
	}

	// 添加
	public String add() {
		// 设置上级部门
		modelDTO.setParent(departmentService.getById(parentId));
		departmentService.save(modelDTO);

		System.out.println(modelDTO.getName());

		return "redirectList";
	}

	// 进入修改页面
	public String editUI() {
		Department department = departmentService.getById(modelDTO.getId());

		// 回显上级部门
		/*
		 * List<Department> departmentList = departmentService.findAll();
		 * ActionContext.getContext().put("departmentList", departmentList);
		 */
		List<Department> departmentList = departmentService.findTopList();
		

		/*
		 * 需要去除此部门的下级部门以及自己，因为不能选择自己和下级部门作为上级部门，否则难以处理，也不合理
		 */
		departmentList = DepartmentUtils.treeListExcept(departmentList, "┠",modelDTO.getId());

		ActionContext.getContext().put("departmentList", departmentList);

		/*
		 * 需要处理当部门没有上级部门时的空指针异常！！
		 */
		if (department.getParent() != null) {
			parentId = department.getParent().getId();
		}

		ActionContext.getContext().getValueStack().push(department);
		return "editUI";
	}

	// 修改
	public String edit() {
		Department department = departmentService.getById(modelDTO.getId());
		department.setName(modelDTO.getName());
		department.setDescription(modelDTO.getDescription());

		// 修改上级部门
		/*
		 * 需要做校验，上级部门不能是此部门原来的下级部门
		 */
		List<Long> childrenIds = DepartmentUtils.getChildrenIds(department);
		
		if (!childrenIds.contains(parentId)) {
			department.setParent(departmentService.getById(parentId));
			departmentService.update(department);
		}
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
