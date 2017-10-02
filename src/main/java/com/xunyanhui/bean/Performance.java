package com.xunyanhui.bean;

import java.util.List;

public class Performance {
	private String id;
	private String title; // 标题
	private String city;// 城市
	private String type;// 类型
	private String time;//开始时间
	private String time_end;//结束时间
	private List<SignupArtist> artistList;
	private int signupNum;// 报名人数
	private String language;// 语言要求
	private String contacts;// 联系人
	private String contactway;// 联系方式
	private String salary;// 薪酬
	private int numberOfApplicants;// 报名人数
	private String gender;// 性别要求
	private String stature;// 身材要求
	private String require;// 活动要求
	private String salaryDescription;// 薪酬要求
	private boolean isSelf;
	private boolean isSignup;// 是否已报名
	private String pic;//用于存储演艺活动的pic的扩展名

	public boolean isSelf() {
		return isSelf;
	}

	public boolean isSignup() {
		return isSignup;
	}

	public void setSignup(boolean isSignup) {
		this.isSignup = isSignup;
	}

	public void setSelf(boolean isSelf) {
		this.isSelf = isSelf;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public List<SignupArtist> getArtistList() {
		return artistList;
	}

	public void setArtistList(List<SignupArtist> artistList) {
		this.artistList = artistList;
	}

	public int getSignupNum() {
		return signupNum;
	}

	public void setSignupNum(int signupNum) {
		this.signupNum = signupNum;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactway() {
		return contactway;
	}

	public void setContactway(String contactway) {
		this.contactway = contactway;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public int getNumberOfApplicants() {
		return numberOfApplicants;
	}

	public void setNumberOfApplicants(int numberOfApplicants) {
		this.numberOfApplicants = numberOfApplicants;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStature() {
		return stature;
	}

	public void setStature(String stature) {
		this.stature = stature;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public String getSalaryDescription() {
		return salaryDescription;
	}

	public void setSalaryDescription(String salaryDescription) {
		this.salaryDescription = salaryDescription;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	

}
