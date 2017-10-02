package com.xunyanhui.model;


/*
 * 系统消息列表实体，用于app中显示
 */
public class SystemMessageList {

	private String id;
	private String details;// 消息详情
	private String releaseTime;// 发布时间
	private String topic;//系统消息的主题
	private int type;//消息类型

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

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
