package com.coolbreeze.oa.service;

import java.util.List;

import com.coolbreeze.oa.base.BaseDao;
import com.coolbreeze.oa.domain.Application;
import com.coolbreeze.oa.domain.ApproveInfo;
import com.coolbreeze.oa.domain.TaskInfo;
import com.coolbreeze.oa.domain.User;

public interface ApplicationService extends BaseDao<Application>{

	void submit(Application application);

	List<TaskInfo> queryMyTask(User currentUser);

	void approve(ApproveInfo approveInfo, String taskId);

	List<Application> getbyApplicant(User currentUser);

}
