package com.clou.inter.service;

import com.clou.enumeration.DataType;
import com.clou.framework.base.IBaseService;
import com.clou.model.Role;

public interface IRoleService extends IBaseService<Role>{

	void getAuthFromAuth(StringBuilder sb, String pauth);

	void getSubAuth(DataType dataType, StringBuilder sb, String parentId);
}
