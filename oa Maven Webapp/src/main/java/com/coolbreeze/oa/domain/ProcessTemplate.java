package com.coolbreeze.oa.domain;

import java.util.Set;

/*
 * 流程模板实体
 */
public class ProcessTemplate {

	private Long id;
	private String name;
	private String path; // 对应的流程模板文件存放路径
	private String ProcessDefKey; // 对应的流程定义key

	private Set<Application> applications;

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getProcessDefKey() {
		return ProcessDefKey;
	}

	public void setProcessDefKey(String processDefKey) {
		ProcessDefKey = processDefKey;
	}

	public Set<Application> getApplications() {
		return applications;
	}

	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}

}
