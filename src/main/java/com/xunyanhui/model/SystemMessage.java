package com.xunyanhui.model;

import java.util.Date;

/*
 * 系统消息表
 */
public class SystemMessage {

	private String id;
	private String details;// 消息详情
	private String releaseTime;// 发布时间
	private String releaseId;// 发布人
	private String acceptId;//接受人id
	private String topic;//系统消息的主题
	private Integer type;//消息的类型

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}

	@Override
	public String toString() {
		return "SystemMessage [id=" + id + ", details=" + details
				+ ", releaseTime=" + releaseTime + ", releaseId=" + releaseId
				+ "]";
	}

	public String getAcceptId() {
		return acceptId;
	}

	public void setAcceptId(String acceptId) {
		this.acceptId = acceptId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
