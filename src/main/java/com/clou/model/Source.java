package com.clou.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.clou.dto.SearchDto;
import com.clou.framework.SpringContextHolder;
import com.clou.framework.base.BaseModel;
import com.clou.inter.service.ICacheService;
import com.clou.service.CacheService;
import com.clou.utils.MyString;



@Entity
@Table(name="source")
@GenericGenerator(name="Generator", strategy="com.clou.framework.IdGenerator")
public class Source extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;// 资源名称
	private String updateTime;
	private String directoryId;// 文件夹目录：dataCenter id
	private String remark; // 资源内容
	private String filePath;
	private String author;//上传者
	private String fileRank;//文件类型
	public String getFileRank() {
		return fileRank;
	}
	public void setFileRank(String fileRank) {
		this.fileRank = fileRank;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Source(){};
	public Source(String id, String createTime, byte status, int sequence, String name, String filePath, String directoryId, String updateTime,
			String author,String fileRank){
		this.id = id;
		this.fileRank = fileRank;
		this.createTime = createTime;
		this.status = status;
		this.sequence = sequence;
		this.name = name;
		this.filePath = filePath;
		this.directoryId = directoryId;
		this.updateTime = updateTime;
		this.author = author;

	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="updateTime")
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name="directoryId")
	public String getDirectoryId() {
		return directoryId;
	}

	public void setDirectoryId(String directoryId) {
		this.directoryId = directoryId;
	}

	@Column(name="remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name="filePath")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Transient
	public String getDirectoryName(){
		if(!MyString.isEmpty(directoryId)){
			if(directoryId != null && directoryId.equals("0")){
				return "根目录";
			}
			ICacheService cacheService = SpringContextHolder.getBean("cacheService", CacheService.class);
			DataCenter module = cacheService.getModule(directoryId);
			if(module!=null)
				return module.getName();
		}
		return "";
	}

	@Transient
	public SearchDto toSearchDto(){
		SearchDto dto = new SearchDto();
		dto.setId(id);
		dto.setCreateTime(createTime);
		dto.setContent(remark == null? "":remark);
		dto.setTitle(name);
		dto.setType(Source.class.getSimpleName());
		dto.setUrl("#/front/source/detail/"+id);
		dto.setVersion("");
		return dto;
	}




}