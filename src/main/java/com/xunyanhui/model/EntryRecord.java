package com.xunyanhui.model;

import java.math.BigDecimal;
import java.util.Date;

/*
 * 报名记录表
 */
public class EntryRecord {


	private String erid;
	private User artist;// 艺人id
	private User employer;// 雇主id
	private Performance performance;// 演艺id
	private Date sigupTime;// 报名时间
	private BigDecimal price;// 成交价格
	private String description;// 成交描述
	private int state;// 执行状态,0表示报名待成交，1表示成交待执行，2表示执行完成待支付，3表示支付完毕，4被取消
	/**
	 * @return the erid
	 */
	public String getErid() {
		return erid;
	}
	/**
	 * @param erid the erid to set
	 */
	public void setErid(String erid) {
		this.erid = erid;
	}
	/**
	 * @return the artistId
	 */
	public User getArtist() {
		return artist;
	}
	/**
	 * @param artistId the artistId to set
	 */
	public void setArtist(User artist) {
		this.artist = artist;
	}
	public User getEmployer() {
		return employer;
	}
	/**
	 * @param employerId the employerId to set
	 */
	public void setEmployer(User employer) {
		this.employer = employer;
	}
	/**
	 * @return the performanceId
	 */
	public Performance getPerformance() {
		return performance;
	}
	/**
	 * @param performanceId the performanceId to set
	 */
	public void setPerformance(Performance performance) {
		this.performance = performance;
	}
	/**
	 * @return the sigupTime
	 */
	public Date getSigupTime() {
		return sigupTime;
	}
	/**
	 * @param sigupTime the sigupTime to set
	 */
	public void setSigupTime(Date sigupTime) {
		this.sigupTime = sigupTime;
	}
	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}



}
