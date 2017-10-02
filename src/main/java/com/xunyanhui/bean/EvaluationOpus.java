package com.xunyanhui.bean;

/*
 * 用于显示艺人发布的小样的评价数据
 */
public class EvaluationOpus {
	
	private String oid;
	private String releaseid;//评价发布人的uid
	private String name;//评价人名称
	private String description;//评价内容
	private String releaseTime;// 评价发布时间
	/**
	 * @return the releaseid
	 */
	public String getReleaseid() {
		return releaseid;
	}
	/**
	 * @param releaseid the releaseid to set
	 */
	public void setReleaseid(String releaseid) {
		this.releaseid = releaseid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the releaseTime
	 */
	public String getReleaseTime() {
		return releaseTime;
	}
	/**
	 * @param releaseTime the releaseTime to set
	 */
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	


}
