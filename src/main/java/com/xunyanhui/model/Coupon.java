package com.xunyanhui.model;

import java.util.Date;

/**
 * 卡卷表
 * 
 * @author 柯鑫
 * 
 */
public class Coupon {
	private String id;
	private String uid; // 账户id
	private String name;// 卡卷名称
	private float sum;// 金额
	private int state;// 使用情况
	private Date beginTime;// 开始时间
	private Date endTime;// 结束时间
	private String sccId;// 卡卷id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getSum() {
		return sum;
	}

	public void setSum(float sum) {
		this.sum = sum;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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

	public String getSccId() {
		return sccId;
	}

	public void setSccId(String sccId) {
		this.sccId = sccId;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", uid=" + uid + ", name=" + name
				+ ", sum=" + sum + ", state=" + state + ", beginTime="
				+ beginTime + ", endTime=" + endTime + ", sccId=" + sccId + "]";
	}

}
