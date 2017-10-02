package com.xunyanhui.model;

import java.math.BigDecimal;
import java.util.Date;

/*
 * 
 * 交易记录表
 * 
 */
public class TransactionRecord {

	private String trid;

	private int type;// 交易类型，1充值，2取现，3支付，4退款
	private String paymentId; // 支付id
	private String paycardId;//支付卡号
	private String receiveId;// 收款id
	private String receivecardId;//
	private Integer money;// 金额
	private String description;// 说明
	private String channel;// 渠道
	private int state;//交易状态
	private Date orderTime;
	private String performanceId;//交易关联的演艺活动id
	
	private String timestamp;//每笔交易的时间戳字符串将来进行安全验证用

	

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	

	public String getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getPerformanceId() {
		return performanceId;
	}

	public void setPerformanceId(String performanceId) {
		this.performanceId = performanceId;
	}
	
	public String getTrid() {
		return trid;
	}

	public void setTrid(String trid) {
		this.trid = trid;
	}

	

	
	public String getReceivecardId() {
		return receivecardId;
	}

	public void setReceivecardId(String receivecardId) {
		this.receivecardId = receivecardId;
	}

	public String getPaycardId() {
		return paycardId;
	}

	public void setPaycardId(String paycardId) {
		this.paycardId = paycardId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransactionRecord [trid=" + trid + ", type=" + type
				+ ", paymentId=" + paymentId + ", paycardId=" + paycardId
				+ ", receiveId=" + receiveId + ", receivecardId="
				+ receivecardId + ", money=" + money + ", description="
				+ description + ", channel=" + channel + ", state=" + state
				+ ", orderTime=" + orderTime + ", performanceId="
				+ performanceId + "]";
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


}
