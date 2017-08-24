package com.clou.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.clou.framework.base.BaseModel;

@Entity
@Table(name="comment")
@GenericGenerator(name="Generator", strategy="com.clou.framework.IdGenerator")
public class Comment extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String webpageId;
	private String userId;
	private String parentId;
	private String content;
	
	
	public Comment(){}
	public Comment(String webpageId){
		this.webpageId = webpageId;
	}
		
	
	public Comment(String webpageId, String userId, String parentId, String content) {
		super();
		this.webpageId = webpageId;
		this.userId = userId;
		this.parentId = parentId;
		this.content = content;
	}
	@Column(name="webpageId")
	public String getWebpageId() {
		return webpageId;
	}
	public void setWebpageId(String webpageId) {
		this.webpageId = webpageId;
	}
	
	@Column(name="userId")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name="parentId")
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Column(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}