package com.xunyanhui.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*
 * 演艺表
 */
public class Performance {

	private String id;
	private Date time;// 演艺时间
	private int days;// 天数

	private String title;// 标题
	private String uid;// user id
	private int userType;// 发布人类型
	private Date releaseTime;// 发布时间

	private int auditState;// 审核状态1审核中 2 审核通过 3 投诉处理中 4 投诉终止

	private String address;// 演艺地址
	private String type;// 演艺类型
	private Date performBeginTime;// 表演开始开始时间
	private Date performEndTime;// 表演结束时间
	private String require;// 要求
	private BigDecimal salary;// 薪酬标准
	private int gender;// 性别要求
	private String salaryDescription;// 薪酬说明
	private String contacts;// 联系人
	private String contactway;// 联系方式
	private String stateDescription;// 状态描述
	private boolean isSignup;// 是否允许登录
	private int signupNum; // 报名人数
	private int numberOfApplicants;// 需要人数
	private Date endTime;// 结束时间
	private Date beginTime; // 报名开始时间
	private int signupState;// 是否允报名 1报名中 2报名截至
	private String stature;// 身材要求

	private String language;// 语言要求

	private String homePic;

	private String pic;// 演艺活动的图片
	private String city;
	private String lat;
	private String lng;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getPerformBeginTime() {
		return performBeginTime;
	}
	public void setPerformBeginTime(Date performBeginTime) {
		this.performBeginTime = performBeginTime;
	}
	public Date getPerformEndTime() {
		return performEndTime;
	}
	public void setPerformEndTime(Date performEndTime) {
		this.performEndTime = performEndTime;
	}
	public String getRequire() {
		return require;
	}
	public void setRequire(String require) {
		this.require = require;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getSalaryDescription() {
		return salaryDescription;
	}
	public void setSalaryDescription(String salaryDescription) {
		this.salaryDescription = salaryDescription;
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
	public String getStateDescription() {
		return stateDescription;
	}
	public void setStateDescription(String stateDescription) {
		this.stateDescription = stateDescription;
	}
	public boolean isSignup() {
		return isSignup;
	}
	public void setSignup(boolean isSignup) {
		this.isSignup = isSignup;
	}
	public int getSignupNum() {
		return signupNum;
	}
	public void setSignupNum(int signupNum) {
		this.signupNum = signupNum;
	}
	public int getNumberOfApplicants() {
		return numberOfApplicants;
	}
	public void setNumberOfApplicants(int numberOfApplicants) {
		this.numberOfApplicants = numberOfApplicants;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public int getSignupState() {
		return signupState;
	}
	public void setSignupState(int signupState) {
		this.signupState = signupState;
	}
	public String getStature() {
		return stature;
	}
	public void setStature(String stature) {
		this.stature = stature;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHomePic() {
		return homePic;
	}
	public void setHomePic(String homePic) {
		this.homePic = homePic;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "Performance [id=" + id + ", time=" + time + ", days=" + days
				+ ", title=" + title + ", uid=" + uid + ", userType="
				+ userType + ", releaseTime=" + releaseTime + ", auditState="
				+ auditState + ", address=" + address + ", type=" + type
				+ ", performBeginTime=" + performBeginTime
				+ ", performEndTime=" + performEndTime + ", require=" + require
				+ ", salary=" + salary + ", gender=" + gender
				+ ", salaryDescription=" + salaryDescription + ", contacts="
				+ contacts + ", contactway=" + contactway
				+ ", stateDescription=" + stateDescription + ", isSignup="
				+ isSignup + ", signupNum=" + signupNum
				+ ", numberOfApplicants=" + numberOfApplicants + ", endTime="
				+ endTime + ", beginTime=" + beginTime + ", signupState="
				+ signupState + ", stature=" + stature + ", language="
				+ language + ", homePic=" + homePic + ", pic=" + pic
				+ ", city=" + city + ", lat=" + lat + ", lng=" + lng + "]";
	}




}
