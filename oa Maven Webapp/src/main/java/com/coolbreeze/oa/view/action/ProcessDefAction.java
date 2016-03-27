package com.coolbreeze.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.coolbreeze.oa.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ProcessDefAction extends BaseAction {

	// 用于上传的临时文件
	private File upload;
	// 用于下载图片的输入流
	private InputStream inputStream;
	// 用于删除时引用的key
	private String key;
	// 用于显示图片时引用的id
	private String id;

	// 列表：显示所有流程部署定义
	public String list() throws Exception {

		List<ProcessDefinition> processDefList = processDefService
				.findAllLastestVersion();

		ActionContext.getContext().put("processDefList", processDefList);

		return "list";
	}

	// 转发到部署添加上传流程文件页面
	public String deployUI() throws Exception {

		return "deployUI";
	}

	// 添加流程部署定义，并重定向回list界面
	public String deploy() throws Exception {

		// 传递zip流
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(
				upload));

		try {
			processDefService.deploy(zipInputStream);
		} finally {
			zipInputStream.close();
		}

		return "redirectList";
	}

	// 删除流程部署
	public String delete() throws Exception {

		//key乱码问题
		//方式一：反向编码
		//key=new String(key.getBytes("iso8859-1"),"utf-8");
		//方式二：客户端二次编码，服务端一次解码
		key=URLEncoder.encode(key, "utf-8");
		
		processDefService.delete(key);
		return "redirectList";
	}

	// 显示流程图
	public String showProcessImg() throws Exception {
	
		System.out.println("id="+id);
		
		id=URLEncoder.encode(id,"utf-8");
		
		System.out.println("id="+id);
		
		inputStream = processDefService.getProcessImgAsStream(id);

		return "showImg";
	}

	// =======================================================

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
