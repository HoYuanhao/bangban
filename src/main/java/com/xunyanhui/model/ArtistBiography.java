package com.xunyanhui.model;

import java.util.Date;

public class ArtistBiography {

	private String id;
	private String name;// 奖项名称
	private String description;// 奖项描述
	private String organization;// 组织方
	private Date time;// 时间
	private Date releaseTime;// 发布时间
	private String releaseid;// 发布id
	private boolean isHighestAward;// 是否是第一名
	private int type;// 类型

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getReleaseid() {
		return releaseid;
	}

	public void setReleaseid(String releaseid) {
		this.releaseid = releaseid;
	}

	public boolean isHighestAward() {
		return isHighestAward;
	}

	public void setHighestAward(boolean isHighestAward) {
		this.isHighestAward = isHighestAward;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ArtistBiography [id=" + id + ", name=" + name
				+ ", description=" + description + ", organization="
				+ organization + ", time=" + time + ", releaseTime="
				+ releaseTime + ", releaseid=" + releaseid
				+ ", isHighestAward=" + isHighestAward + ", type=" + type + "]";
	}

}
