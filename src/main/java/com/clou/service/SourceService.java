package com.clou.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.clou.framework.base.BaseService;
import com.clou.framework.base.IBaseDao;
import com.clou.inter.service.ISourceService;
import com.clou.model.Source;

@Service
public class SourceService extends BaseService<Source>
		implements ISourceService {

	@Resource(name="sourceDao")
	public void setDao(IBaseDao<Source> dao ) {
		super.setDao(dao, new Source());
	}
}
