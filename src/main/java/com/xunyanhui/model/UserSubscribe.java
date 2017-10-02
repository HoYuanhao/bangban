package com.xunyanhui.model;

import java.util.Date;

/*
 * 用户对演艺活动的订阅
 */
public class UserSubscribe {

	private int subId;//自增性的主键
	private String uid;// 订阅人id
	private int pid;//演艺活动类别id
	private Date subTime;// 订阅时间
	private int subState;// 订阅状态，0-取消订阅，1-订阅
	
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public Date getSubTime() {
		return subTime;
	}
	public void setSubTime(Date subTime) {
		this.subTime = subTime;
	}
	public int getSubState() {
		return subState;
	}
	public void setSubState(int subState) {
		this.subState = subState;
	}

	

}
