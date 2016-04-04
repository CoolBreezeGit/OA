package com.coolbreeze.oa.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.coolbreeze.oa.domain.User;
import com.coolbreeze.oa.service.ApplicationService;
import com.coolbreeze.oa.service.DepartmentService;
import com.coolbreeze.oa.service.ProcessDefService;
import com.coolbreeze.oa.service.ProcessTemplateService;
import com.coolbreeze.oa.service.RoleService;
import com.coolbreeze.oa.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

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
	@Resource
	protected ApplicationService applicationService;
	
	/*
	 * 得到当前用户
	 */
	public User getCurrentUser(){
		return (User) ActionContext.getContext().getSession().get("user");
	}
	
	
	/*
	 * 保存上传的临时文件，返回文件保存路径
	 */
	protected String saveUploadFile(File upload) {
		// 得到真实路径，末尾没有'/'
		String basePath1 = ServletActionContext.getServletContext()
				.getRealPath("/WEB-INF/uploadFile");
		// 目录打散:使用年/月/日的文件夹分层格式
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		String basePath2 = sdf.format(new Date());

		// 如果不存在，则创建文件夹链
		File file = new File(basePath1 + basePath2);
		if (!file.exists()) {
			file.mkdirs();
		}

		// 使用UUID防止文件重名
		String path = basePath1 + basePath2 + UUID.randomUUID().toString();
		// 移动uplaod临时文件到/WEB-INF/uploadFile文件家下
		upload.renameTo(new File(path));

		return path;
	}
}
