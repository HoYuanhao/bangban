package com.xunyanhui.model;

import java.util.Date;

/*
 * 作品点赞表
 */
public class FavoriteOpus {

	private String id;
	private String oid; // 被评价作品Id
	private String uid;// 评价id
	private Date time;// 评价id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
