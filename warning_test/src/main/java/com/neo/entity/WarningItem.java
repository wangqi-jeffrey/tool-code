package com.neo.entity;

import java.util.Date;

public class WarningItem {

	private Integer id;
	private String name;
	private Boolean sendSms;
	private Boolean sendEmail;
	private String smsTpl;
	private String emailTitle;
	private String emailTpl;
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

	public String getSmsTpl() {
		return smsTpl;
	}

	public void setSmsTpl(String smsTpl) {
		this.smsTpl = smsTpl;
	}

	public String getEmailTitle() {
		return emailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}

	public String getEmailTpl() {
		return emailTpl;
	}

	public void setEmailTpl(String emailTpl) {
		this.emailTpl = emailTpl;
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

	public Boolean getSendSms() {
		return sendSms;
	}

	public void setSendSms(Boolean sendSms) {
		this.sendSms = sendSms;
	}

	public Boolean getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(Boolean sendEmail) {
		this.sendEmail = sendEmail;
	}

	public Boolean getInvalid() {
		return invalid;
	}

	public void setInvalid(Boolean invalid) {
		this.invalid = invalid;
	}

	@Override
	public String toString() {
		return "WarningItem [id=" + id + ", name=" + name + ", sendSms="
				+ sendSms + ", sendEmail=" + sendEmail + ", smsTpl=" + smsTpl
				+ ", emailTitle=" + emailTitle + ", emailTpl=" + emailTpl
				+ ", invalid=" + invalid + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

}