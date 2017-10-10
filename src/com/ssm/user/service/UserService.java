package com.ssm.user.service;

import com.ssm.base.service.BaseService;
import com.ssm.user.domain.UserDO;


public interface UserService extends BaseService<UserDO, Integer> {
	
	
	int saveUser(UserDO userDO) throws Exception;

}
