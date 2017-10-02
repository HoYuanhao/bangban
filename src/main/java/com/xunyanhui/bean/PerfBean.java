package com.xunyanhui.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Administrator
 * 
 */
public class PerfBean {
	private String uid; // 用户id
	private Date releaseTime;// 发布时间

	private String title;// 标题
	private String address;// 详细地址
	private String lng;
	private String lat;// 经纬度
	private String city;// 城市
	private long performBeginTime;// 表演开始开始时间
	private long performEndTime;// 表演结束时间
	private int type;// 演绎类型
	private String require;// 要求说明
	private BigDecimal salary; // 薪酬
	private int gender;// 需要性别
	private String salaryDescription; // 薪酬说明
	private int artistNum;// 需要艺人人数
	private String contacts;// 联系人
	private String contactway;// 联系方式
	private long beginTime; // 报名开始时间

	
	private long endTime; // 报名截至时间
	private String stature;// 身材要求
	private String language;// 语言要求

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "PerfBean [uid=" + uid + ", releaseTime=" + releaseTime
				+ ", title=" + title + ", address=" + address + ", lng=" + lng
				+ ", lat=" + lat + ", city=" + city + ", performBeginTime="
				+ performBeginTime + ", performEndTime=" + performEndTime
				+ ", type=" + type + ", require=" + require + ", salary="
				+ salary + ", gender=" + gender + ", salaryDescription="
				+ salaryDescription + ", artistNum=" + artistNum
				+ ", contacts=" + contacts + ", contactway=" + contactway
				+ ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", stature=" + stature + ", language=" + language + "]";
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getPerformBeginTime() {
		return performBeginTime;
	}

	public void setPerformBeginTime(long performBeginTime) {
		this.performBeginTime = performBeginTime;
	}

	public long getPerformEndTime() {
		return performEndTime;
	}

	public void setPerformEndTime(long performEndTime) {
		this.performEndTime = performEndTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public int getArtistNum() {
		return artistNum;
	}

	public void setArtistNum(int artistNum) {
		this.artistNum = artistNum;
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

	

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
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
	
	public long getBeginTime() {
		return beginTime;
	}

	
	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}


}
