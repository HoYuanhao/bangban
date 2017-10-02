package com.xunyanhui.bean;


/*
 * app端读取用户对演艺活动的订阅情况
 */
public class UserSubscribeV {

	
	@Override
	public String toString() {
		return "UserSubscribeV [uid=" + uid + ", pid=" + pid + ", subState=" + subState + "]";
	}
	private String uid;// 订阅人id
	private int pid;//演艺活动类别id
	private int subState;// 订阅状态，0-取消订阅，1-订阅
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
	
	public int getSubState() {
		return subState;
	}
	public void setSubState(int subState) {
		this.subState = subState;
	}

	

}
