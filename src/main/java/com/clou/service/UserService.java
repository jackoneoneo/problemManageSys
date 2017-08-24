package com.clou.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.clou.framework.base.BaseService;
import com.clou.framework.base.IBaseDao;
import com.clou.inter.service.IUserService;
import com.clou.model.User;

@Service
public class UserService extends BaseService<User>
		implements IUserService {

	@Resource(name="userDao")
	public void setDao(IBaseDao<User> dao) {
		super.setDao(dao, new User());
	}
}
