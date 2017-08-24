package com.clou.dao;

import org.springframework.stereotype.Repository;

import com.clou.framework.base.BaseDao;
import com.clou.inter.dao.IErrorDao;
import com.clou.model.Error;

@Repository("errorDao")
public class ErrorDao extends BaseDao<Error> implements IErrorDao {

}