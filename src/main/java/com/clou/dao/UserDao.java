package com.clou.dao;

import org.springframework.stereotype.Repository;

import com.clou.framework.base.BaseDao;
import com.clou.inter.dao.IUserDao;
import com.clou.model.User;

@Repository("userDao")
public class UserDao extends BaseDao<User>
		implements IUserDao {

}