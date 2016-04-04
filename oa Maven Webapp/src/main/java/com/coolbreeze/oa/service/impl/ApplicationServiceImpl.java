package com.coolbreeze.oa.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;

import com.coolbreeze.oa.base.BaseDaoImpl;
import com.coolbreeze.oa.domain.Application;
import com.coolbreeze.oa.domain.ApproveInfo;
import com.coolbreeze.oa.domain.TaskInfo;
import com.coolbreeze.oa.domain.User;
import com.coolbreeze.oa.service.ApplicationService;

@Service
public class ApplicationServiceImpl extends BaseDaoImpl<Application> implements ApplicationService{

	/*
	 * 提交流程申请
	 * 保存申请信息，启动流程实例，保存申请信息为流程变量
	 */
	public void submit(Application application) {
		
		/*
		 * 保存application
		 */
		//设置申请时间：当前时间
		application.setApplyTime(new Date());
		
		//设置申请名称：申请流程文件模板名+申请人+申请时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		application.setName(application.getProcessTemplate().getName()
				+"_"+application.getApplicant().getLoginName()
				+"_"+sdf.format(application.getApplyTime()));
		
		//设置申请流程状态
		application.setStatus(Application.STATUS_APPROVING);
		
		//保存
		getSession().save(application);
		
		
		/*
		 * 启动流程实例
		 */
		//流程变量
		Map<String,Object> varMap=new HashMap<String, Object>();
		varMap.put("application", application);
		ProcessInstance pi=processEngine.getExecutionService().startProcessInstanceByKey(application.getProcessTemplate().getProcessDefKey(), varMap);
		
		//办完此提交申请流程
		Task task=processEngine.getTaskService()
					.createTaskQuery()
					.processInstanceId(pi.getId())		//得到此流程实例中目前唯一的任务：提交申请
					.uniqueResult();
		processEngine.getTaskService().completeTask(task.getId());
	}

	/*
	 * 查询当前用户的个人任务列表
	 */
	public List<TaskInfo> queryMyTask(User currentUser) {
		
		
		List<Task> taskList=processEngine.getTaskService()
					.findPersonalTasks(currentUser.getLoginName());
		
		List<TaskInfo> taskInfoList=new ArrayList<TaskInfo>();
		
		for(Task task : taskList){
			TaskInfo taskInfo=new TaskInfo();
			
			//任务
			taskInfo.setTask(task);
			//任务对应的申请信息变量
			taskInfo.setApplication((Application) processEngine.getTaskService().getVariable(task.getId(), "application"));
			
			taskInfoList.add(taskInfo);
		}
		
		return taskInfoList;
		
	}
	
	/*
	 * 审批
	 * 1.保存审批信息
	 * 2.办理任务
	 * 3.维护application的状态
	 */
	public void approve(ApproveInfo approveInfo,String taskId) {

		//保存审批信息
		getSession().save(approveInfo);
		
		
		//先取出task，便于后边使用，否则执行完任务后取不到此对象
		Task task=processEngine.getTaskService().getTask(taskId);
		
		//办理任务
		processEngine.getTaskService().completeTask(taskId);
		
		//通过executionId获取流程实例，以此判断流程是否处于最后一个节点
		ProcessInstance pi=processEngine.getExecutionService().findProcessInstanceById(task.getExecutionId());
		
		//得到application
		Application application=approveInfo.getApplication();
		
		//维护申请的状态：通过，未通过，审批中
		if(approveInfo.getApproval()){	//同意		
			if(pi == null){		//处于最后一个节点
				//设置申请的状态为通过
				application.setStatus(Application.STATUS_SUCCESSED);
			}
		}else{		//不同意
			if(pi != null){		//不是处于最后一个审批节点
				//结束此实例
				processEngine.getExecutionService().endProcessInstance(pi.getId(), ProcessInstance.STATE_ENDED);
			}
			//设置申请的状态为未通过
			application.setStatus(Application.STATUS_FAILED);
		}
		//更新application
		getSession().update(application);
	}

	public List<Application> getbyApplicant(User currentUser) {
		
		return getSession().createQuery("From Application Where applicant is ?")	//
					.setParameter(0, currentUser) 	//
					.list();	// 
	}
	
}
