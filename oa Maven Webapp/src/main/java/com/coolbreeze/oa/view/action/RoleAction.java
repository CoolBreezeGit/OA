package com.coolbreeze.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.coolbreeze.oa.base.ModelDrivenBaseAction;
import com.coolbreeze.oa.domain.Role;
import com.coolbreeze.oa.service.RoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class RoleAction extends ModelDrivenBaseAction<Role>{

	// 显示
	public String list() {	
		List<Role> roleList=roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		//ActionContext.getContext().getValueStack().push(roleList);
		return "list";
	}

	// 删除
	public String delete() {
		//System.out.println(modelDTO.getId());
		roleService.delete(modelDTO.getId());
		//roleService.delete(id);
		return "redirectList";
	}

	// 进入添加页面
	public String addUI() {		
		return "addUI";
	}

	// 添加
	public String add() {
		//System.out.println(modelDTO.getName());
		roleService.save(modelDTO);
		return "redirectList";
	}

	// 进入修改页面
	public String editUI() {
		Role role=roleService.getById(modelDTO.getId());
		//ActionContext.getContext().put("role", role);
		/*
		 * 为什么下面这句会引发异常？？？？为什么现在重新构建项目后又不会了。。。。。？？？
		 * failed to lazily initialize a collection of role: com.coolbreeze.oa.domain.Role.users, 
		 * could not initialize proxy - no Session - Class: org.hibernate.collection.internal.AbstractPersistentCollection
		 */
		ActionContext.getContext().getValueStack().push(role);
		return "editUI";
	}

	// 修改
	public String edit() {
		//System.out.println(modelDTO.getId()+" : "+modelDTO.getName()+" : "+modelDTO.getDescription());
		Role role=roleService.getById(modelDTO.getId());
		role.setName(modelDTO.getName());
		role.setDescription(modelDTO.getDescription());
		roleService.update(role);
		return "redirectList";
	}

}
