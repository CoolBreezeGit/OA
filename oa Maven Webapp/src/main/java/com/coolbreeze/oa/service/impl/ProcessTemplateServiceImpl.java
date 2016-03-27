package com.coolbreeze.oa.service.impl;

import java.io.File;

import org.springframework.stereotype.Service;

import com.coolbreeze.oa.base.BaseDaoImpl;
import com.coolbreeze.oa.domain.ProcessTemplate;
import com.coolbreeze.oa.service.ProcessTemplateService;

@Service
public class ProcessTemplateServiceImpl extends BaseDaoImpl<ProcessTemplate> implements ProcessTemplateService{

	
	/*
	 * 重写删除方法：还需要删除文件夹中的文件
	 */
	@Override
	public void delete(Long id) {
	
		ProcessTemplate pt=getById(id);
		
		File file=new File(pt.getPath());
		
		file.delete();
		
		getSession().delete(pt);
		
	}
}
