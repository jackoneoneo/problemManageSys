package com.clou.service;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clou.enumeration.DataType;
import com.clou.framework.base.BaseService;
import com.clou.framework.base.IBaseDao;
import com.clou.inter.service.IDataCenterService;
import com.clou.inter.service.IRoleService;
import com.clou.model.DataCenter;
import com.clou.model.Role;
import com.clou.utils.Tools;

@Service
public class RoleService extends BaseService<Role>
		implements IRoleService {
	@Autowired
	private IDataCenterService moduleService;
	@Resource(name="roleDao")
	public void setDao(IBaseDao<Role> dao) {
		super.setDao(dao, new Role());
	}
	@Override
	@Transactional
	public void getAuthFromAuth(StringBuilder sb, String pauth){
		sb.append(pauth+",");
		for(String auth:pauth.split(",")){
			if(auth.startsWith(DataType.MODULE.name()+"_")){
				getSubAuth(DataType.MODULE,sb,auth.split("_")[1]);
			}else if(auth.startsWith(DataType.INTERFACE.name()+"_")){
				getSubAuth(DataType.INTERFACE,sb,auth.split("_")[1]);
			}
		}
	}
	
	@Transactional
	@Override
	public void getSubAuth(DataType dataType,StringBuilder sb,String parentId){
		for(DataCenter module :moduleService.findByMap(Tools.getMap("parentId",parentId), null, null)){
			sb.append(dataType.name()+"_"+module.getId()+",");
			getSubAuth(dataType,sb,module.getId());
		}
	}
}
