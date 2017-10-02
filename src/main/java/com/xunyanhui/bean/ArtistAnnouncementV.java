package com.xunyanhui.bean;


/*
 * 艺人通告
 */
public class ArtistAnnouncementV {

	private String aid;
	/*
	 * 通告内容
	 */
	private String content;
	/*
	 * 起始时间
	 */
	private String beginTime;
	/*
	 * 结束时间
	 */
	private String endTime;
	/*
	 * 通告状态
	 */
	private int state;
	private String releaseTime;//通告的发布时间

	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

}
