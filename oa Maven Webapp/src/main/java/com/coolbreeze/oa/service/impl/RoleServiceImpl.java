package com.coolbreeze.oa.service.impl;

import org.springframework.stereotype.Service;

import com.coolbreeze.oa.base.BaseDaoImpl;
import com.coolbreeze.oa.domain.Role;
import com.coolbreeze.oa.service.RoleService;

@Service
//必须要开启事务，否则getCurrentSession不能成功！！
public class RoleServiceImpl extends BaseDaoImpl<Role> implements RoleService{


}
