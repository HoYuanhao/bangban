package com.xunyanhui.model;

import java.util.Date;

/*
 * 用户关注
 */
public class UserAttention {

	private String id;
	private int type;// 被关注对象类型
	private String concernId;// 被关注对象id
	private String uid;// 关注人id
	private Date concernTime;// 关注时间
	private int state;// 状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getConcernId() {
		return concernId;
	}

	public void setConcernId(String concernId) {
		this.concernId = concernId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Date getConcernTime() {
		return concernTime;
	}

	public void setConcernTime(Date concernTime) {
		this.concernTime = concernTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "UserAttention [id=" + id + ", type=" + type + ", concernId="
				+ concernId + ", uid=" + uid + ", concernTime=" + concernTime
				+ ", state=" + state + "]";
	}

}
