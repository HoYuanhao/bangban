/**
 * 创建日期：2017-3-02上午11:45:26
 * 修改日期：
 * 作者：邢传军
 * 对承担某一演艺活动的艺人的投诉信息
 */
package com.xunyanhui.model;

import java.util.Date;

public class Complain {
	private int cid;//投诉id
	private String uid;//投诉人id
	private String artistid;//被投诉人id
	private String performanceid;//被投诉的演艺活动id
	private Date complaintime;//投诉时间
	private String description;//投诉内容描述
	private int state;//投诉活动状态
	private String result;//投诉处理结果
	private String opid;//投诉处理人id
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getArtistid() {
		return artistid;
	}
	public void setArtistid(String artistid) {
		this.artistid = artistid;
	}
	public String getPerformanceid() {
		return performanceid;
	}
	public void setPerformanceid(String performanceid) {
		this.performanceid = performanceid;
	}
	public Date getComplaintime() {
		return complaintime;
	}
	public void setComplaintime(Date complaintime) {
		this.complaintime = complaintime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getOpid() {
		return opid;
	}
	public void setOpid(String opid) {
		this.opid = opid;
	}
	
	

}
