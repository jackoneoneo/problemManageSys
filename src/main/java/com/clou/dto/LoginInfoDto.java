package com.clou.dto;

import java.io.Serializable;
import java.util.List;

import com.clou.enumeration.DataCeneterType;
import com.clou.enumeration.DataType;
import com.clou.enumeration.UserType;
import com.clou.inter.service.IDataCenterService;
import com.clou.inter.service.IRoleService;
import com.clou.model.Role;
import com.clou.model.User;
import com.clou.utils.Const;
import com.clou.utils.Tools;

public class LoginInfoDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private String userName;
	private String trueName;
	private String authStr;// 权限，由用户权限、角色拼接而成
	private String roleId; 
	private String id;
	private String auth;
	public String getAuth() {
		return auth;
	}
	private byte type;
	
	public LoginInfoDto(User user, IRoleService roleService, IDataCenterService dataCenterService){
		this.userName = user.getUserName();
		this.trueName = user.getTrueName();
		this.roleId = user.getRoleId();
		
		this.id = user.getId();
		this.type = user.getType();
		
		StringBuilder sb = new StringBuilder(",");
		roleService.getAuthFromAuth(sb, user.getAuth());
		this.auth=sb.toString();
		if (user.getRoleId() != null && !user.getRoleId().equals("")) {
			List<Role> roles = roleService.findByMap(
					Tools.getMap("id|in", Tools.getIdsFromField(user.getRoleId())), null, null);
			// 递归将子模块权限添加至用户权限中
			for (Role role : roles) {
				roleService.getAuthFromAuth(sb, role.getAuth());
			}
		}
		
		// 将用户的自己的模块添加至权限中
		List<String> moduleIds = dataCenterService.getList(  null, DataCeneterType.MODULE.name(), user.getId() );
		for(String moduleId:moduleIds){
			sb.append(DataType.MODULE.name()+"_" + moduleId+",");
			sb.append(DataType.INTERFACE.name()+"_" + moduleId+",");
			sb.append(DataType.ERROR.name()+"_" + moduleId+",");
			roleService.getSubAuth(DataType.MODULE, sb, moduleId);
			roleService.getSubAuth(DataType.INTERFACE,sb, moduleId);
		}
		
		// 100
		if( (user.getType() + "").equals(UserType.管理员.getName()) ){
			sb.append(Const.AUTH_ADMIN+",");
		}else{
			sb.append("MODULE_privateModule,");
		}
		this.authStr = sb.toString();
	}

	public String getUserName() {
		return userName;
	}

	public String getTrueName() {
		return trueName;
	}

	public String getAuthStr() {
		if(authStr == null)
			return "";
		return authStr;
	}

	public String getRoleId() {
		return roleId;
	}
	
	public String getId(){
		return id;
	}
	
	public byte getType(){
		return type;
	}

}
