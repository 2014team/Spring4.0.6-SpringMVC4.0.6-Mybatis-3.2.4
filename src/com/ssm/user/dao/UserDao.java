package com.ssm.user.dao;

import org.springframework.stereotype.Repository;

import com.ssm.base.dao.BaseDao;
import com.ssm.user.domain.UserDO;

@Repository
public interface UserDao extends BaseDao<UserDO, Integer> {

}