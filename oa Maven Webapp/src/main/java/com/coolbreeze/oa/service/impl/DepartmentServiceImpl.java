package com.coolbreeze.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolbreeze.oa.base.BaseDaoImpl;
import com.coolbreeze.oa.domain.Department;
import com.coolbreeze.oa.service.DepartmentService;

@Service
public class DepartmentServiceImpl extends BaseDaoImpl<Department> implements DepartmentService{

	public List<Department> findTopList() {
		return getSession().createQuery("From Department Where parent Is NULL").list();
	}


	public List<Department> findChildrenList(Long parentId) {
		return getSession().createQuery("From Department Where parent.id is ?")
				.setParameter(0, parentId).list();
	}


}
