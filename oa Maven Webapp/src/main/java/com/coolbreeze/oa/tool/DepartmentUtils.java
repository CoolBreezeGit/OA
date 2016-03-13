package com.coolbreeze.oa.tool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.coolbreeze.oa.domain.Department;

/*
 * 通过递归构建树状显示形式的部门列表
 */
public class DepartmentUtils {

	public static List<Department> treeList(
			Collection<Department> departmentList, String prefix) {

		List<Department> list = new ArrayList<Department>();

		recursionTree1(departmentList, list, prefix);

		return list;

	}

	public static void recursionTree1(Collection<Department> departmentList,
			List<Department> list, String prefix) {

		for (Department department : departmentList) {
			Department copy = new Department();
			copy.setId(department.getId());
			copy.setName(prefix + department.getName());
			list.add(copy);
			recursionTree1(department.getChildren(), list, "　" + prefix);
		}
	}

	/*
	 * 树状显示部门列表，自己和下级部门除外！！
	 */
	public static List<Department> treeListExcept(
			List<Department> departmentList, String prefix,Long id) {
		
		List<Department> list = new ArrayList<Department>();
		recursionTree2(departmentList, list, prefix,id);

		return list;
	}
	
	
	public static void recursionTree2(Collection<Department> departmentList,
			List<Department> list, String prefix,Long id) {

		for (Department department : departmentList) {
			//需要排除指定部门的自身和下级部门
			if(id != department.getId()){
				Department copy = new Department();
				copy.setId(department.getId());
				copy.setName(prefix + department.getName());
				list.add(copy);
				recursionTree2(department.getChildren(), list, "　" + prefix,id);
			}
		}
	}
	
	
	/*
	 * 得到指定部门的所有下级部门的id
	 */
	public static List<Long> getChildrenIds(Department department) {
		List<Long> ids = new ArrayList<Long>();
		recursionGet(department, ids);
		return ids;
	}

	public static void recursionGet(Department department, List<Long> ids) {
		for (Department child : department.getChildren()) {
			ids.add(child.getId());
			recursionGet(child, ids);
		}
	}



}
