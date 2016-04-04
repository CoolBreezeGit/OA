package com.coolbreeze.oa.domain;

import java.util.Date;
import java.util.Set;

/*
 * 申请实体
 */
public class Application {

	private Long id;
	private String name;
	private Date applyTime;		//申请事件
	
	//三种状态：审批中，未通过，已通过
	public static final String STATUS_APPROVING="审批中";
	public static final String STATUS_SUCCESSED="已通过";
	public static final String STATUS_FAILED="未通过";
	
	private String status;		//三种状态之一
	
	private String filePath;	//申请流程模板路径，使用上传下载的方式来修改申请文件信息

	private User applicant;		//申请人

	private Set<ApproveInfo> approveInfos;	//审批信息，多个

	private ProcessTemplate processTemplate;	//所用的流程模板文件

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}

	public Set<ApproveInfo> getApproveInfos() {
		return approveInfos;
	}

	public void setApproveInfos(Set<ApproveInfo> approveInfos) {
		this.approveInfos = approveInfos;
	}

	public ProcessTemplate getProcessTemplate() {
		return processTemplate;
	}

	public void setProcessTemplate(ProcessTemplate processTemplate) {
		this.processTemplate = processTemplate;
	}

}
