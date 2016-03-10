package com.coolbreeze.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.coolbreeze.oa.base.BaseAction;
import com.coolbreeze.oa.domain.Department;
import com.coolbreeze.oa.domain.Role;
import com.coolbreeze.oa.domain.User;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private String departmentId;

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	

	// 显示
	public String list() {

		List<User> userList = userService.findAll();
		
		System.out.println(userList.get(0).getName());
		
		/*
		 * 为什么这次到这句会发生异常。。。。！！！！？？？？？
		 */
		ActionContext.getContext().put("userList", userList);
		//ActionContext.getContext().getValueStack().push(userList);

		return "list";
	}

	// 删除
	public String delete() {

		userService.delete(modelDTO.getId());

		return "redirectList";
	}

	// 进入添加页面
	public String addUI() {

		// TODO 准备数据：部门和岗位
		List<Department> departmentList = departmentService.findAll();
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		ActionContext.getContext().put("roleList", roleList);

		return "addUI";
	}

	// 添加
	public String add() {
		System.out.println(modelDTO.getName());
		userService.add(modelDTO);
		return "redirectList";
	}

	// 进入修改页面
	public String editUI() {
		User user = userService.getById(modelDTO.getId());
		ActionContext.getContext().getValueStack().push(user);
		return "editUI";
	}

	// 修改
	public String edit() {
		User user = userService.getById(modelDTO.getId());
		user.setName(modelDTO.getName());
		user.setDescription(modelDTO.getDescription());
		userService.update(user);
		return "redirectList";
	}
}
