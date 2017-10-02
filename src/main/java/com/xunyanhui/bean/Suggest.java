package com.xunyanhui.bean;

/**
 * 创建日期：2017-3-17下午17:45:26
 * 修改日期：
 * 作者：邢传军
 * 对产品的反馈和建议
 */


import java.util.Date;

public class Suggest {
	private int sid;//建议id
	private String uid;//建议人id
	private Date suggestTime;//建议时间
	private String description;//建议内容描述
	private int state;//建议活动状态
	private String result;//建议处理结果
	private String opid;//建议处理人id
	private String contact;//意见提交人的联系方式
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public Date getSuggestTime() {
		return suggestTime;
	}
	public void setSuggestTime(Date suggestTime) {
		this.suggestTime = suggestTime;
	}
	
	

}
