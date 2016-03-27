package com.coolbreeze.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.coolbreeze.oa.base.ModelDrivenBaseAction;
import com.coolbreeze.oa.domain.ProcessTemplate;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ProcessTemplateAction extends
		ModelDrivenBaseAction<ProcessTemplate> {

	private File upload; // 用于上传流程模板文件
	private InputStream inputStream; // 用于下载流程模板文件

	/*
	 * 列表
	 */
	public String list() throws Exception {

		System.out.println("list");
		List<ProcessTemplate> processTemplateList = processTemplateService
				.findAll();

		ActionContext.getContext().put("processTemplateList",
				processTemplateList);
		System.out.println("list2");
		return "list";
	}

	/*
	 * 添加页面
	 */
	public String addUI() throws Exception {
		// 需要准备所有最新版本的流程定义key，以便关联
		List<ProcessDefinition> processDefList = processDefService
				.findAllLastestVersion();

		ActionContext.getContext().put("processDefList", processDefList);

		return "addUI";
	}

	/*
	 * 添加：需要保存流程模板doc文件
	 */
	public String add() throws Exception {

		/*
		 * 保存流程doc文件到文件夹中
		 */
		//得到真实路径，末尾没有'/'
		String basePath1=ServletActionContext.getServletContext().getRealPath("/WEB-INF/uploadFile");
		//目录打散:使用年/月/日的文件夹分层格式
		SimpleDateFormat sdf=new SimpleDateFormat("/yyyy/MM/dd/");
		String basePath2=sdf.format(new Date());
		
		//如果不存在，则创建文件夹链
		File file=new File(basePath1+basePath2);
		if(!file.exists()){
			file.mkdirs();
		}
		
		//使用UUID防止文件重名
		String path=basePath1+basePath2+UUID.randomUUID().toString();
		//移动uplaod临时文件到/WEB-INF/uploadFile文件家下
		upload.renameTo(new File(path));
		
		/*
		 * 保存path到信息到数据库
		 */
		modelDTO.setPath(path);
		processTemplateService.save(modelDTO);
		
		return "redirectList";
	}

	// 删除
	public String delete() throws Exception {

		processTemplateService.delete(modelDTO.getId());

		return "redirectList";
	}

	// 下载流程模板doc文件
	public String download() throws Exception{

		ProcessTemplate processTemplate = processTemplateService
				.getById(modelDTO.getId());
		inputStream=new FileInputStream(processTemplate.getPath());

		return "download";
	}

	// ==================================================

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
