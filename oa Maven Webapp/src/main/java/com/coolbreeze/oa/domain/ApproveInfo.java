package com.coolbreeze.oa.domain;

import java.util.Date;

/*
 * 审批信息实体
 */
public class ApproveInfo {

	private Long id;
	private Date approveTime;	//审批事件
	private String comment;		//批注，审批意见
	private boolean approval;	//审批结果，同意或者不同意

	private Application application;
	private User approver;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


	public boolean getApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public User getApprover() {
		return approver;
	}

	public void setApprover(User approver) {
		this.approver = approver;
	}

}
