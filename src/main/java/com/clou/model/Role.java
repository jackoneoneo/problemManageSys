package com.clou.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.clou.framework.base.BaseModel;

/**
 * @author zengzhipeng
 *
 */
@Entity
@Table(name="role")
@GenericGenerator(name="Generator", strategy="com.clou.framework.IdGenerator")
public class Role extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String roleName;
	private String auth;
	private String authName;
	@Column(name="auth")
	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	@Column(name="authName")
	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	@Column(name="roleName")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}