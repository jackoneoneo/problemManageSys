package com.clou.dao;

import org.springframework.stereotype.Repository;

import com.clou.framework.base.BaseDao;
import com.clou.inter.dao.IMenuDao;
import com.clou.model.Menu;

@Repository("menuDao")
public class MenuDao extends BaseDao<Menu>
		implements IMenuDao {

}