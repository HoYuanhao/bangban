package com.xunyanhui.model;

import java.util.Date;

/*
 * 艺人擅长详情表
 */
public class ArtistAdept {

	private String id;
	private String uid;
	private String typeId;// 类型
	private Date setTime;// 设置时间
	private String setPeopleId;// 设定人

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public Date getSetTime() {
		return setTime;
	}

	public void setSetTime(Date setTime) {
		this.setTime = setTime;
	}

	public String getSetPeopleId() {
		return setPeopleId;
	}

	public void setSetPeopleId(String setPeopleId) {
		this.setPeopleId = setPeopleId;
	}

	@Override
	public String toString() {
		return "ArtistAdept [id=" + id + ", uid=" + uid + ", typeId=" + typeId
				+ ", setTime=" + setTime + ", setPeopleId=" + setPeopleId + "]";
	}

}
