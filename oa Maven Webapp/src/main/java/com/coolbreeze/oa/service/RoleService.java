package com.coolbreeze.oa.service;

import java.util.Collection;
import java.util.List;

import com.coolbreeze.oa.domain.Role;

public interface RoleService {

	List<Role> findAll();

	void delete(Long id);

	void add(Role role);

	Role getById(Long id);

	void update(Role role);

	List<Role> getByIds(Long[] roleIds);

}
