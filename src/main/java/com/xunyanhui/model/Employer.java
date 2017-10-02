package com.xunyanhui.model;

/*
 * 雇主表
 */
public class Employer {

	private String id;
	private int releaseNum;// 发布数
	private int dealNum;// 成交数
	private int highNum;// 好评数
	private int badNum;// 差评数
	private int state;// 用户状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getReleaseNum() {
		return releaseNum;
	}

	public void setReleaseNum(int releaseNum) {
		this.releaseNum = releaseNum;
	}

	public int getDealNum() {
		return dealNum;
	}

	public void setDealNum(int dealNum) {
		this.dealNum = dealNum;
	}

	public int getHighNum() {
		return highNum;
	}

	public void setHighNum(int highNum) {
		this.highNum = highNum;
	}

	public int getBadNum() {
		return badNum;
	}

	public void setBadNum(int badNum) {
		this.badNum = badNum;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
