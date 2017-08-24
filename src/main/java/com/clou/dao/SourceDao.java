package com.clou.dao;

import org.springframework.stereotype.Repository;

import com.clou.framework.base.BaseDao;
import com.clou.inter.dao.ISourceDao;
import com.clou.model.Source;

@Repository("sourceDao")
public class SourceDao extends BaseDao<Source>
		implements ISourceDao {

}