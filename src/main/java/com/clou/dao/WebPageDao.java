package com.clou.dao;

import org.springframework.stereotype.Repository;

import com.clou.framework.base.BaseDao;
import com.clou.inter.dao.IWebPageDao;
import com.clou.model.WebPage;

@Repository("webPageDao")
public class WebPageDao extends BaseDao<WebPage> implements IWebPageDao {
}