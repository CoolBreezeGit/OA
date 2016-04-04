package com.coolbreeze.oa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.coolbreeze.oa.base.BaseDaoImpl;
import com.coolbreeze.oa.domain.User;
import com.coolbreeze.oa.service.UserService;

@Service
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {

	/*
	 * 通过登录名和密码来查找对应的用户
	 */
	public User findByLoginNameAndPassword(String loginName, String password) {
		return (User) getSession()	//
					.createQuery("From User Where loginName=? And password=?")	//
					.setParameter(0,loginName)	//
					.setParameter(1, DigestUtils.md5DigestAsHex(password.getBytes())) 	//
					.uniqueResult();
	}
	
}
