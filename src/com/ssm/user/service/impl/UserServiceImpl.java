package com.ssm.user.service.impl;
import org.springframework.stereotype.Service;

import com.ssm.base.service.impl.BaseServiceImpl;
import com.ssm.user.entity.User;
import com.ssm.user.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Integer> implements UserService{

}
