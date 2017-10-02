package com.xunyanhui.model;

import java.util.Date;

/*
 * 系统内私信表
 */
public class SystemPrivateMessage {
	private String id;
	private String releaseId;// 发布人
	private String acceptId;// 接受人
	private Date releaseTime;// 发布人时间
	private String details;// 消息详情
	private boolean isRead;// 是否读取

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}

	public String getAcceptId() {
		return acceptId;
	}

	public void setAcceptId(String acceptId) {
		this.acceptId = acceptId;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	@Override
	public String toString() {
		return "SystemPrivateMessage [id=" + id + ", releaseId=" + releaseId
				+ ", acceptId=" + acceptId + ", releaseTime=" + releaseTime
				+ ", details=" + details + ", isRead=" + isRead + "]";
	}

}
