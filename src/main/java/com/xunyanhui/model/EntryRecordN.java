package com.xunyanhui.model;

import java.math.BigDecimal;
import java.util.Date;

/*
 * 报名记录表
 * 根据实体EntryRecord修改而来
 * 2017-01-02
 */
public class EntryRecordN {

	private String erid;
	private String artistid;// 艺人id
	private String performanceid;// 演艺id
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
	public String getArtistid() {
		return artistid;
	}
	public void setArtistid(String artistid) {
		this.artistid = artistid;
	}
	public String getPerformanceid() {
		return performanceid;
	}
	public void setPerformanceid(String performanceid) {
		this.performanceid = performanceid;
	}



}
