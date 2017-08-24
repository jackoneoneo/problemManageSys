package com.clou.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.clou.framework.base.BaseService;
import com.clou.framework.base.IBaseDao;
import com.clou.inter.dao.IWebPageDao;
import com.clou.inter.service.IWebPageService;
import com.clou.model.WebPage;

@Service
public class WebPageService extends BaseService<WebPage>
		implements IWebPageService {
	@Resource(name="webPageDao")
	IWebPageDao webPageDao;

	@Resource(name="webPageDao")
	public void setDao(IBaseDao<WebPage> dao) {
		super.setDao(dao, new WebPage());
	}
	
}
