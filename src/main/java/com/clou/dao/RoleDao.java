package com.clou.dao;

import org.springframework.stereotype.Repository;

import com.clou.framework.base.BaseDao;
import com.clou.inter.dao.IRoleDao;
import com.clou.model.Role;

@Repository("roleDao")
public class RoleDao extends BaseDao<Role>
		implements IRoleDao {

}