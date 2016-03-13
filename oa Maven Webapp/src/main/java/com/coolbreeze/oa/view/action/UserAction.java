package com.coolbreeze.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;

import com.coolbreeze.oa.base.BaseAction;
import com.coolbreeze.oa.domain.Department;
import com.coolbreeze.oa.domain.Role;
import com.coolbreeze.oa.domain.User;
import com.coolbreeze.oa.tool.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private Long departmentId;
	private Long[] roleIds;

	
	// 显示
	public String list() {

		List<User> userList = userService.findAll();
		ActionContext.getContext().put("userList", userList);

		return "list";
	}

	// 删除
	public String delete() {

		userService.delete(modelDTO.getId());

		return "redirectList";
	}	
	
	// 进入添加页面
	public String addUI() {

		// TODO 准备数据：部门和岗位，要以树状结构显示！！
		List<Department> departmentList = departmentService.findTopList();
		departmentList=DepartmentUtils.treeList(departmentList, "┠");
		
		ActionContext.getContext().put("departmentList", departmentList);
		
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "addUI";
	}

	// 添加
	public String add() {
		// 设置部门
		modelDTO.setDepartment(departmentService.getById(departmentId));
		// 设置岗位
		modelDTO.setRoles(new HashSet<Role>(roleService.getByIds(roleIds)));

		//默认设置密码为1234
		modelDTO.setPassword( DigestUtils.md5DigestAsHex("1234".getBytes()));
		
		userService.add(modelDTO);
		return "redirectList";
	}

	// 进入修改页面
	public String editUI() {
		//准备部门和岗位的数据
		List<Department> departmentList = departmentService.findTopList();
		departmentList=DepartmentUtils.treeList(departmentList, "┠");
		
		ActionContext.getContext().put("departmentList", departmentList);
		
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		//准备用户的数据
		User user = userService.getById(modelDTO.getId());
			
		//设置departmentId和roleIds，方便回显
		
		/*
		 * 当用户没有所属部门时，需要处理空异常
		 */
		if(user.getDepartment()!=null)
			departmentId=user.getDepartment().getId();
		/*
		 * 当用户没有岗位时，需要处理空异常
		*/
		if(user.getRoles()!=null){
			int size=user.getRoles().size();
			roleIds=new Long[size];
			int i=0;
			for(Role role : user.getRoles()){
				roleIds[i++]=role.getId();
				
			}
		}
		
		ActionContext.getContext().getValueStack().push(user);
		return "editUI";
	}

	// 修改
	public String edit() {
		User user = userService.getById(modelDTO.getId());
		
		user.setLoginName(modelDTO.getLoginName());
		user.setName(modelDTO.getName());
		user.setDescription(modelDTO.getDescription());
		user.setGender(modelDTO.getGender());
		user.setEmail(modelDTO.getEmail());
		user.setPhoneNumber(modelDTO.getPhoneNumber());
		
		
		// 设置部门
		user.setDepartment(departmentService.getById(departmentId));
		// 设置岗位
		user.setRoles(new HashSet<Role>(roleService.getByIds(roleIds)));

		
		userService.update(user);
		return "redirectList";
	}
	
	
	
	//初始化密码
	public String initPassword(){
		User user = userService.getById(modelDTO.getId());
		//初始化密码为1234
		//System.out.println("password");
		//System.out.println(DigestUtils.md5Hex("1234"));
		System.out.println(DigestUtils.md5DigestAsHex("1234".getBytes()));
		user.setPassword(DigestUtils.md5DigestAsHex("1234".getBytes()));
		userService.update(user);
		return "redirectList";
	}
	
	//================================================
	
	
	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

}
