package com.coolbreeze.oa.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolbreeze.oa.service.ProcessDefService;

@Service
@Transactional
public class ProcessDefServiceImpl implements ProcessDefService {

	@Resource
	private ProcessEngine processEngine;

	public List<ProcessDefinition> findAllLastestVersion() {
		// 1.得到所有的流程定义
		List<ProcessDefinition> processDefinitionList = processEngine
				.getRepositoryService() //
				.createProcessDefinitionQuery() //
				.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION)// 根据版本来升序排列
				.list();

		// 2.得到最新版本的流程定义：使用map来提取最新版本的流程定义
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();
		for (ProcessDefinition pd : processDefinitionList) {
			map.put(pd.getKey(), pd);
		}

		// 3.返回最新版本流程定义list
		return new ArrayList<ProcessDefinition>(map.values());
	}

	public void deploy(ZipInputStream zipInputStream) {

		processEngine.getRepositoryService() //
				.createDeployment() //
				.addResourcesFromZipInputStream(zipInputStream) //
				.deploy();

	}

	public void delete(String key) {

		//得到所有的流程定义：通过key
		List<ProcessDefinition> pdList=processEngine.getRepositoryService()	//
					.createProcessDefinitionQuery() //
					.processDefinitionKey(key)	//
					.list();

		//通过循环流程定义列表得到对应的部署对象的id
		for(ProcessDefinition pd : pdList){
			processEngine.getRepositoryService().deleteDeployment(pd.getDeploymentId());
		}
		
	}

	public InputStream getProcessImgAsStream(String id) {

		//通过key得到流程定义
		ProcessDefinition pd=processEngine.getRepositoryService()	//
						.createProcessDefinitionQuery()	//
						.processDefinitionId(id)	//
						.uniqueResult();	//
		
		return processEngine.getRepositoryService()	//
				.getResourceAsStream(pd.getDeploymentId(), pd.getImageResourceName());
		
	}

}
