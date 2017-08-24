package com.clou.dao;

import org.springframework.stereotype.Repository;

import com.clou.framework.base.BaseDao;
import com.clou.inter.dao.ILogDao;
import com.clou.model.Log;

@Repository("logDao")
public class LogDao extends BaseDao<Log>
		implements ILogDao {

}