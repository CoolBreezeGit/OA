package com.coolbreeze.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolbreeze.oa.dao.DepartmentDao;
import com.coolbreeze.oa.domain.Department;
import com.coolbreeze.oa.service.DepartmentService;

@Service
@Transactional		//必须要开启事务，否则getCurrentSession不能成功！！
public class DepartmentServiceImpl implements DepartmentService{

	@Resource
	private DepartmentDao departmentDao;
	
	public List<Department> findAll() {
		return departmentDao.findAll();
	}

	public void delete(Long id) {	
		departmentDao.delete(id);
	}

	public void add(Department Department) {
		departmentDao.save(Department);
	}

	public Department getById(Long id) {
		return departmentDao.getById(id);
	}

	public void update(Department Department) {
		departmentDao.update(Department);
	}

}
