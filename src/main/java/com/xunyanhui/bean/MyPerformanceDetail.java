package com.xunyanhui.bean;

import java.util.List;
/*
 * 用于我发布的演艺活动的演示显示详情
 */
public class MyPerformanceDetail {
	private String id;
	private String title; // 标题
	private String city;// 城市
	private String type;// 类型
	private String time;// 时间
	private List<SignupArtistOfPerf> artistList;
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
	private String releaseTime;
	private int auditState;
	private String address;
	private String performBeginTime;
	private String performEndTime;
	private String endTime;
	private String beginTime;
	private int signupState;
	
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<SignupArtistOfPerf> getArtistList() {
		return artistList;
	}

	public void setArtistList(List<SignupArtistOfPerf> artistList) {
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

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public int getAuditState() {
		return auditState;
	}

	public void setAuditState(int auditState) {
		this.auditState = auditState;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPerformBeginTime() {
		return performBeginTime;
	}

	public void setPerformBeginTime(String performBeginTime) {
		this.performBeginTime = performBeginTime;
	}

	public String getPerformEndTime() {
		return performEndTime;
	}

	public void setPerformEndTime(String performEndTime) {
		this.performEndTime = performEndTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public int getSignupState() {
		return signupState;
	}

	public void setSignupState(int signupState) {
		this.signupState = signupState;
	}

}
