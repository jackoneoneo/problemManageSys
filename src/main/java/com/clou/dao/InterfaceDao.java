package com.clou.dao;

import org.springframework.stereotype.Repository;

import com.clou.framework.base.BaseDao;
import com.clou.inter.dao.IInterfaceDao;
import com.clou.model.Interface;

@Repository("interfaceDao")
public class InterfaceDao extends BaseDao<Interface>
		implements IInterfaceDao {

}