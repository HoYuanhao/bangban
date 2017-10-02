package com.xunyanhui.model;

import java.util.Date;

/*
 * 活动消息
 */
public class ActiveMessage {

	private String id;
	private String details;// 活动详情
	private Date releaseTime;// 发布时间
	private String releaseId;// 发布人
	private String img;//活动消息的封面图片
	private String scope;//活动消息推送范围
	private String topic;//活动消息的主题
	
	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

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

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
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
		return "ActiveMessage [id=" + id + ", details=" + details
				+ ", releaseTime=" + releaseTime + ", releaseId=" + releaseId
				+ "]";
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
