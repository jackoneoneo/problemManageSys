package com.clou.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.clou.framework.base.BaseService;
import com.clou.framework.base.IBaseDao;
import com.clou.inter.service.IErrorService;
import com.clou.model.Error;

@Service
public class ErrorService extends BaseService<Error>
		implements IErrorService {

	@Resource(name="errorDao")
	public void setDao(IBaseDao<Error> dao) {
		super.setDao(dao, new Error());
	}
}
