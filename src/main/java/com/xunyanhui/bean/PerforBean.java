package com.xunyanhui.bean;

public class PerforBean {
	private String id; // id
	private String titile;// 标题
	private String time;// 时间
	private String address;// 地址
	private int signupNum;// 报名人数
	private String picPath;// 图像地址
	private String salary;// 薪酬
	private int signupState;// 报名状态 0 未报名 1已报名 2 已录用 3已付款
	private String city;//演艺活动所在城市
	private int distance;

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitile() {
		return titile;
	}

	public void setTitile(String titile) {
		this.titile = titile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSignupNum() {
		return signupNum;
	}

	public void setSignupNum(int signupNum) {
		this.signupNum = signupNum;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public int getSignupState() {
		return signupState;
	}

	public void setSignupState(int signupState) {
		this.signupState = signupState;
	}


	public String getCity() {
		return city;
	}

	
	public void setCity(String city) {
		this.city = city;
	}

}
