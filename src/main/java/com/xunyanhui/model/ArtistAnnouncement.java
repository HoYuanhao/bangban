package com.xunyanhui.model;

import java.util.Date;

/*
 * 艺人通告
 */
public class ArtistAnnouncement {

	private String id;
	private String aid;
	private String uid;
	/*
	 * 通告内容
	 */
	private String content;
	/*
	 * 起始时间
	 */
	private Date beginTime;
	/*
	 * 结束时间
	 */
	private Date endTime;
	/*
	 * 通告状态
	 */
	private int state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	private Date releaseTime;//通告的发布时间

	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ArtistAnnouncement [id=" + id + ", content=" + content
				+ ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", state=" + state + "]";
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

}
