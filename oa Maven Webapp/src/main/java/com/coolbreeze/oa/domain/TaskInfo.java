package com.coolbreeze.oa.domain;

import org.jbpm.api.task.Task;

/*
 * 任务信息：保护jbpm的task和对于的任务申请信息
 */
public class TaskInfo {

	private Task task;
	private Application application;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

}
