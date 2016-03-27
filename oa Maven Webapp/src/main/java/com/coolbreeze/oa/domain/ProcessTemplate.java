package com.coolbreeze.oa.domain;

/*
 * 流程模板实体
 */
public class ProcessTemplate {

	private Long id;
	private String name;
	private String path; // 对应的流程模板文件存放路径
	private String ProcessDefKey; // 对应的流程定义key

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

}
