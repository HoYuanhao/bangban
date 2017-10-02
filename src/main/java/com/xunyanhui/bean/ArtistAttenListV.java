package com.xunyanhui.bean;

import java.math.BigDecimal;

/*
 * 我关注的艺人列表
 */
public class ArtistAttenListV {
	private String id;
	private String stageName;// 艺名
	private String performanceType;// 演绎类型
	private int completeNum;// 完成数
	private int fanNum;// 粉丝数
	private float rateOfpraise;// 好评率
	private String city;
	private String pic;//艺人头像
	private boolean isV;// 是否加V 
	private BigDecimal minSalary;//
	private int isAuth;//是否实名
	private int honestyLevel;//诚信等级
	private String attentionTime;//用户关注时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getPerformanceType() {
		return performanceType;
	}

	public void setPerformanceType(String performanceType) {
		this.performanceType = performanceType;
	}

	

	public int getCompleteNum() {
		return completeNum;
	}

	public void setCompleteNum(int completeNum) {
		this.completeNum = completeNum;
	}

	public int getFanNum() {
		return fanNum;
	}

	public void setFanNum(int fanNum) {
		this.fanNum = fanNum;
	}

	public float getRateOfpraise() {
		return rateOfpraise;
	}

	public void setRateOfpraise(float rateOfpraise) {
		this.rateOfpraise = rateOfpraise;
	}


	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	public BigDecimal getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(BigDecimal minSalary) {
		this.minSalary = minSalary;
	}
	public boolean isV() {
		return isV;
	}
	public void setV(boolean isV) {
		this.isV = isV;
	}
	public int getIsAuth() {
		return isAuth;
	}
	public void setIsAuth(int isAuth) {
		this.isAuth = isAuth;
	}
	public int getHonestyLevel() {
		return honestyLevel;
	}
	public void setHonestyLevel(int honestyLevel) {
		this.honestyLevel = honestyLevel;
	}
	public String getAttentionTime() {
		return attentionTime;
	}
	public void setAttentionTime(String attentionTime) {
		this.attentionTime = attentionTime;
	}

}
