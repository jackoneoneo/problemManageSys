package com.clou.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.clou.enumeration.MenuType;
import com.clou.framework.base.BaseModel;
import com.clou.utils.MyString;


/**
 * @date 2016-01-06
 */
@Entity
@Table(name="menu")
@GenericGenerator(name="Generator", strategy="com.clou.framework.IdGenerator")
public class Menu extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * menuName(菜单名称)
	 * */
	private String menuName;
	/**
	 * menuUrl(菜单链接)
	 * */
	private String menuUrl;
	/**
	 * roleIds(角色可见集合  （ID之间以逗号分隔）)
	 * */
	private String roleIds;//待删除
	
	private String parentId;
	private String iconRemark;
	private String type;

	@Transient
	public String getTypeName(){
		if(!MyString.isEmpty(type))
			return MenuType.valueOf(type).getName();
		return "";
	}
	
	@Column(name="menuName")
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name="menuUrl")
	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Column(name="roleIds")
	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	@Column(name="parentId")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Column(name="iconRemark")
	public String getIconRemark() {
		return iconRemark;
	}

	public void setIconRemark(String iconRemark) {
		this.iconRemark = iconRemark;
	}
	@Column(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}