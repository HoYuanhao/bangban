package com.xunyanhui.bean;

import java.math.BigDecimal;
import java.util.List;
/*
 * 用于处理在我报名的演艺的数据，包含了具体报名人的相关信息
 */
public class PerformanceOfEntry {
	private String id;
	private String title; // 标题
	private String city;// 城市
	private String type;// 类型
	private String time;// 演艺活动的开始时间
	private String time_end;//演艺活动的结束时间
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
	private String pic;//演艺活动的图片扩展名.pic
	private String artistid;//报名艺人id
	private String entryTime;//报名时间
	private BigDecimal price;//报名价格
	private String desc;//报名薪酬说明
	

	
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

	public String getArtistid() {
		return artistid;
	}

	public void setArtistid(String artistid) {
		this.artistid = artistid;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the isSelf
	 */
	public boolean isSelf() {
		return isSelf;
	}

	/**
	 * @param isSelf the isSelf to set
	 */
	public void setSelf(boolean isSelf) {
		this.isSelf = isSelf;
	}

	/**
	 * @return the isSignup
	 */
	public boolean isSignup() {
		return isSignup;
	}

	/**
	 * @param isSignup the isSignup to set
	 */
	public void setSignup(boolean isSignup) {
		this.isSignup = isSignup;
	}

	/**
	 * @return the time_end
	 */
	public String getTime_end() {
		return time_end;
	}

	/**
	 * @param time_end the time_end to set
	 */
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

}
