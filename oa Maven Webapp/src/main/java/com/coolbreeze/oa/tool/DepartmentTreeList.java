package com.coolbreeze.oa.tool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.coolbreeze.oa.domain.Department;

/*
 * 通过递归构建树状显示形式的部门列表
 */
public class DepartmentTreeList{

	public static List<Department> treeList(
			Collection<Department> departmentList,String prefix){
		
		List<Department> list=new ArrayList<Department>();
		
		recursion(departmentList,list,prefix);
		
		return list;
		
	}
	
	public static void recursion(Collection<Department> departmentList, List<Department> list, String prefix){

		for(Department department : departmentList){
			Department copy=new Department();
			copy.setId(department.getId());
			copy.setName(prefix+department.getName());
			list.add(copy);
			recursion(department.getChildren(),list,"　"+prefix);
		}
	}
	
}
