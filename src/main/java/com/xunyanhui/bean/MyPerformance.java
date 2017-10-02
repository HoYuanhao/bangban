package com.xunyanhui.bean;

import java.math.BigDecimal;


/*
 * 我发布的演艺列表-所需要的演艺活动的数据
 */
public class MyPerformance {

	private String id;
	private String title;// 标题
	private String releaseTime;// 发布时间
	private int auditState;// 审核状态1审核中 2 审核通过 3 投诉处理中 4 投诉终止
	private String type;// 演艺类型
	private String performBeginTime;// 表演开始开始时间
	private String performEndTime;// 表演结束时间
	private String require;// 要求
	private BigDecimal salary;// 薪酬标准
	private int signupNum; // 报名人数
	private int numberOfApplicants;// 需要人数
	private int signupState;// 是否允报名 1报名中 2报名截至
	private String city;//演艺活动所在城市

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
