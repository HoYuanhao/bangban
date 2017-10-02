/**
 * 创建日期：2016-12-18下午4:25:13
 * 修改日期：
 * 作者：邢传军
 * 目的：用于显示艺人接单演艺的文字评价情况，与视图art_preformance_evaluation对应
 */
package com.xunyanhui.model;

public class ArtPerformanceEvaluation {
	private String apeId;//评价信息id
	
	private String title;//演艺活动名称
	private String description;//评价详细信息
	private String releaseTime;//评价的发布时间
	private String userName;//评价人的用户名
	private String acceptId;//被评价人id
	private int state;//演艺活动的执行状态执行情况，0表示尚未成交,1表示成交待执行，2表示成交完成待支付，3表示成交支付完毕，4被取消
	
	/**
	 * @return the apeId
	 */
	public String getApeId() {
		return apeId;
	}
	/**
	 * @param apeId the apeId to set
	 */
	public void setApeId(String apeId) {
		this.apeId = apeId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAcceptId() {
		return acceptId;
	}
	public void setAcceptId(String acceptId) {
		this.acceptId = acceptId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	

}
