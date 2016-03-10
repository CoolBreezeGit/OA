package com.coolbreeze.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.coolbreeze.oa.base.BaseDaoImpl;
import com.coolbreeze.oa.dao.DepartmentDao;
import com.coolbreeze.oa.domain.Department;

@Repository
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao{

	public List<Department> findTopList() {
		
		return getSession().createQuery("From Department Where parent is null").list();
		
	}

}
