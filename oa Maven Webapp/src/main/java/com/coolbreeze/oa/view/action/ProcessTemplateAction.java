package com.coolbreeze.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

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

		List<ProcessTemplate> processTemplateList = processTemplateService
				.findAll();

		ActionContext.getContext().put("processTemplateList",
				processTemplateList);
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
		System.out.println(upload.getName());
		String path = saveUploadFile(upload);

		/*
		 * 保存path到信息到数据库
		 */
		modelDTO.setPath(path);
		processTemplateService.save(modelDTO);

		return "redirectList";
	}


	//
	public String editUI() {

		// 需要准备所有最新版本的流程定义key，以便关联
		List<ProcessDefinition> processDefList = processDefService
				.findAllLastestVersion();
		ActionContext.getContext().put("processDefList", processDefList);
		
		//回显
		ProcessTemplate pt=processTemplateService.getById(modelDTO.getId());
		ActionContext.getContext().getValueStack().push(pt);

		return "editUI";
	}

	//
	public String edit() {
		
		System.out.println(modelDTO.getId());
		
		//得到要修改的模板
		ProcessTemplate pt=processTemplateService.getById(modelDTO.getId());
		
		//修改名称和所用流程定义
		pt.setName(modelDTO.getName());
		pt.setProcessDefKey(modelDTO.getProcessDefKey());

		//如果修改了模板文件，则修改
		if(upload != null){
			
			//删除旧文件
			File oldFile=new File(pt.getPath());
			if(oldFile.exists()){
				oldFile.delete();
			}
			
			//保存新文件的路径
			pt.setPath(saveUploadFile(upload));
		}
		//更新
		processTemplateService.update(pt);
		
		return "redirectList";
	}

	// 删除
	public String delete() throws Exception {

		processTemplateService.delete(modelDTO.getId());

		return "redirectList";
	}

	// 下载流程模板doc文件
	public String download() throws Exception {

		ProcessTemplate processTemplate = processTemplateService
				.getById(modelDTO.getId());

		String fileName = "doc文件";
		ActionContext.getContext().put("fileName", fileName);

		inputStream = new FileInputStream(processTemplate.getPath());

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
