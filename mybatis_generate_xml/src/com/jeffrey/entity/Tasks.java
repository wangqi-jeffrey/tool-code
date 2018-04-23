package com.jeffrey.entity;

import java.util.Date;

public class Tasks {
    private Integer id;

    private String taskType;

    private Integer billId;

    private String userCode;

    private Integer qty;

    private Date createDate;

    private String creator;

    private String taskDesc;

    private String remark;

    private Date confirmDate;

    private Integer isCase;

    private Integer taskLock;

    private Date stickDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc == null ? null : taskDesc.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Integer getIsCase() {
        return isCase;
    }

    public void setIsCase(Integer isCase) {
        this.isCase = isCase;
    }

    public Integer getTaskLock() {
        return taskLock;
    }

    public void setTaskLock(Integer taskLock) {
        this.taskLock = taskLock;
    }

    public Date getStickDate() {
        return stickDate;
    }

    public void setStickDate(Date stickDate) {
        this.stickDate = stickDate;
    }
}