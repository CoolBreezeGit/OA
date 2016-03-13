package com.coolbreeze.oa.tool;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.coolbreeze.oa.domain.Department;
import com.coolbreeze.oa.service.DepartmentService;

public class DepartmentTreeListTest {
	
	@Test
	public void test() {
		ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:beans.xml");
		DepartmentService departmentService=(DepartmentService) ac.getBean("departmentService");
		
		List<Department> topList = departmentService.findTopList();
		System.out.println(topList);
		
		List<Department> departmentList=new ArrayList<Department>();
		DepartmentUtils.treeList(topList, "┠");
		
		System.out.println(departmentList);
	}

}
