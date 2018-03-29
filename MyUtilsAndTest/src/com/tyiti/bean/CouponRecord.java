package com.tyiti.bean;

import java.util.Date;

public class CouponRecord {
	// Fields
	private Integer id;
	private Integer couId;
	private Integer custId;
	private String mobile;
	private String couCode;
	private Date receiveTime;
	private Byte isUsed;
	private Date useTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCouId() {
		return couId;
	}

	public void setCouId(Integer couId) {
		this.couId = couId;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCouCode() {
		return couCode;
	}

	public void setCouCode(String couCode) {
		this.couCode = couCode;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Byte getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Byte isUsed) {
		this.isUsed = isUsed;
	}

	public Date getUseTime() {
		return useTime;
	}

	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}

	@Override
	public String toString() {
		return "CouponRecord [id=" + id + ", couId=" + couId + ", custId="
				+ custId + ", mobile=" + mobile + ", couCode=" + couCode
				+ ", receiveTime=" + receiveTime + ", isUsed=" + isUsed
				+ ", useTime=" + useTime + "]";
	}
}