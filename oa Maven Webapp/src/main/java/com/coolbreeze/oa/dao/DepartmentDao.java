package com.coolbreeze.oa.dao;

import java.util.List;

import com.coolbreeze.oa.base.BaseDao;
import com.coolbreeze.oa.domain.Department;

public interface DepartmentDao extends BaseDao<Department>{

	List<Department> findTopList();

}
