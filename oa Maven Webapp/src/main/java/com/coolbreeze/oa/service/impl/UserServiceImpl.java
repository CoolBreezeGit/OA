package com.coolbreeze.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coolbreeze.oa.dao.UserDao;
import com.coolbreeze.oa.domain.User;
import com.coolbreeze.oa.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	public List<User> findAll() {
		return userDao.findAll();
	}

	public void delete(Long id) {
		userDao.delete(id);
	}

	public void add(User user) {
		userDao.save(user);
	}

	public User getById(Long id) {
		return userDao.getById(id);
	}

	public void update(User user) {
		userDao.update(user);
	}

}
