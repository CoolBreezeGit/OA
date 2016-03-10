package com.coolbreeze.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolbreeze.oa.dao.RoleDao;
import com.coolbreeze.oa.domain.Role;
import com.coolbreeze.oa.service.RoleService;

@Service
@Transactional		//必须要开启事务，否则getCurrentSession不能成功！！
public class RoleServiceImpl implements RoleService{

	@Resource
	private RoleDao roleDao;
	
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	public void delete(Long id) {	
		roleDao.delete(id);
	}

	public void add(Role role) {
		roleDao.save(role);
	}

	public Role getById(Long id) {
		return roleDao.getById(id);
	}

	public void update(Role role) {
		roleDao.update(role);
	}

}
