package com.coolbreeze.oa.service;

import java.util.List;

import com.coolbreeze.oa.base.BaseDao;
import com.coolbreeze.oa.domain.User;

public interface UserService extends BaseDao<User>{

	User findByLoginNameAndPassword(String loginName, String password);


}
