package com.xunyanhui.model;



/*
 * 用户对某一对象的关注结果表
 */
public class UserAttentionV {

	private String uaId;
	private int state;// 状态

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUaId() {
		return uaId;
	}

	public void setUaId(String uaId) {
		this.uaId = uaId;
	}

	

}
