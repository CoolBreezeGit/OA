package com.coolbreeze.oa.service;

import java.util.List;

import com.coolbreeze.oa.domain.User;

public interface UserService {

	List<User> findAll();

	void delete(Long id);

	void add(User user);

	User getById(Long id);

	void update(User user);


}
