package com.coolbreeze.oa.service;

import java.util.List;

import com.coolbreeze.oa.domain.Department;

public interface DepartmentService {

	List<Department> findAll();

	void delete(Long id);

	void add(Department Department);

	Department getById(Long id);

	void update(Department Department);

	List<Department> findTopList();

}
