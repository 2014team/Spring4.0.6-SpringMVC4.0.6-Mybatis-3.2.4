package com.ssm.user.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.base.service.impl.BaseServiceImpl;
import com.ssm.user.dao.UserDao;
import com.ssm.user.domain.UserDO;
import com.ssm.user.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDO, Integer> implements UserService{

	@Autowired
	UserDao userDao;
	
	public int saveUser(UserDO userDO) throws Exception {
		userDao.save(userDO);
		//int i = 1/0;
		return userDO.getId();
	}

}
