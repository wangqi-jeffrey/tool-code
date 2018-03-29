package com.neo.entity;

import java.util.Date;

public class WarningUser {

	private Integer id;
	private String name;
	private String mobile;
	private String email;
	private Boolean invalid;
	private Date createTime;
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getInvalid() {
		return invalid;
	}

	public void setInvalid(Boolean invalid) {
		this.invalid = invalid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "WarningUser [id=" + id + ", name=" + name + ", mobile="
				+ mobile + ", email=" + email + ", invalid=" + invalid
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}

}