package com.coolbreeze.oa.service;

import java.util.List;

import com.coolbreeze.oa.base.BaseDao;
import com.coolbreeze.oa.domain.Department;

public interface DepartmentService extends BaseDao<Department> {

	List<Department> findTopList();


	List<Department> findChildrenList(Long parentId);

}
