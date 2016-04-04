package com.coolbreeze.oa.view.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.coolbreeze.oa.base.BaseAction;
import com.coolbreeze.oa.domain.Application;
import com.coolbreeze.oa.domain.ApproveInfo;
import com.coolbreeze.oa.domain.ProcessTemplate;
import com.coolbreeze.oa.domain.TaskInfo;
import com.coolbreeze.oa.domain.User;
import com.opensymphony.xwork2.ActionContext;

/*
 * 此Action用于处理流程审批，有起草申请，审批，查询审批信息和申请信息等功能
 */
@Controller
@Scope("prototype")
public class JobFlowAction extends BaseAction {

	private Long processTemplateId;
	private File upload;

	private String taskId;
	private boolean approval;
	private Long applicationId;
	private String comment;

	// 流程申请------------------------------------------------------------------------------
	/*
	 * 起草申请UI1：选择流程模板文件
	 */
	public String applicationUI1() {

		// 准备所有流程模板文件
		List<ProcessTemplate> processTemplateList = processTemplateService
				.findAll();

		ActionContext.getContext().put("processTemplateList",
				processTemplateList);

		return "applicationUI1";

	}

	/*
	 * 起草申请UI2：下载上传流程模板文件
	 */
	public String applicationUI2() {

		return "applicationUI2";

	}

	/*
	 * 提交申请
	 */
	public String application() {
		System.out.println("application");
		// 构建流程申请实体
		Application application = new Application();

		// 保存流程模板申请文件
		String filePath = saveUploadFile(upload);
		application.setFilePath(filePath);

		// 设置申请人：当前会话用户
		application.setApplicant(getCurrentUser());

		// 设置使用的流程模板文件
		application.setProcessTemplate(processTemplateService
				.getById(processTemplateId));

		// 交给service层完成提交申请的工作
		applicationService.submit(application);

		return "redirectMyAppQuery"; // 重定向到我的申请查询

	}

	// --------------------------------------------------------------------------------------

	// 申请查询--------------------------------------------------------------------------------------
	public String queryMyApp() {

		List<Application> applications=applicationService.getbyApplicant(getCurrentUser());
		
		ActionContext.getContext().put("applications", applications);
		
		return "applicationList";
	}

	// --------------------------------------------------------------------------------------

	// 流程审批------------------------------------------------------------------------------
	/*
	 * 待我审批，查询我的个人任务列表
	 */
	public String queryMyTask() {

		List<TaskInfo> taskInfoList = applicationService
				.queryMyTask(getCurrentUser());
		System.out.println(taskInfoList);
		ActionContext.getContext().put("taskInfoList", taskInfoList);

		return "taskList";
	}

	/*
	 * 审批处理UI
	 */
	public String approveUI() {
		return "approveUI";
	}

	/*
	 * 审批处理
	 */
	public String approve() {

		ApproveInfo approveInfo = new ApproveInfo();
		
		//设置审批信息中的值
		approveInfo.setApproval(approval);
		approveInfo.setComment(comment);
		approveInfo.setApprover(getCurrentUser());
		approveInfo.setApproveTime(new Date());
		approveInfo.setApplication(applicationService.getById(applicationId));
		
		applicationService.approve(approveInfo,taskId);
		
		return "redirectQueryMyTask";
	}

	/*
	 * 查看流程流转记录
	 */
	public String queryProcessRecord() {

		List<ApproveInfo> approveInfos=
				new ArrayList(applicationService.getById(applicationId).getApproveInfos());
		
		ActionContext.getContext().put("approveInfos", approveInfos);
		
		return "processRecord";
	}

	// --------------------------------------------------------------------------------------

	public Long getProcessTemplateId() {
		return processTemplateId;
	}

	public void setProcessTemplateId(Long processTemplateId) {
		this.processTemplateId = processTemplateId;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
}