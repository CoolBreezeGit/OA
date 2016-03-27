package com.coolbreeze.oa.service;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.jbpm.api.ProcessDefinition;

public interface ProcessDefService {

	/*
	 * 得到所有的最新版本的流程定义
	 */
	List<ProcessDefinition> findAllLastestVersion();

	/*
	 * 接收zip流，部署流程定义
	 */
	void deploy(ZipInputStream zipInputStream);

	/*
	 * 根据key删除流程部署对象
	 */
	void delete(String key);

	/*
	 * 根据id得到流程定义流程图片
	 */
	InputStream getProcessImgAsStream(String id);

}
