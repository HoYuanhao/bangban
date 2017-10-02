package com.xunyanhui.model;

import java.math.BigDecimal;
import java.util.Date;

/*
 * 系统发布卡卷表
 */
public class SystemCardCoupons {

	private String id;
	private String name;// 卡卷名称
	private String description;// 卡卷描述
	private Date beginTime;// 发放开始时间
	private Date endTime;// 发放结束时间
	private BigDecimal money;// 金额
	private float scale;// 现金折算比例
	private String approvalid;// 审批人id
	private Date approvaltime;// 审批时间
	private int count;// 发放数

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getApprovalid() {
		return approvalid;
	}

	public void setApprovalid(String approvalid) {
		this.approvalid = approvalid;
	}

	public Date getApprovaltime() {
		return approvaltime;
	}

	public void setApprovaltime(Date approvaltime) {
		this.approvaltime = approvaltime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
