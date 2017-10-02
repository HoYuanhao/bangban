package com.xunyanhui.bean;

public class ArtiArtist {

	private String id;// id
	private String headPortrait;// 头像
	private String stageName;// 艺名
	private boolean isV;// 是否加V
	private int fanNum;// 粉丝数
	private int completeNum;// 完成数
	private String specialtys;// 特长
	private String address; // 地址
	private String minSalary;// 最低出场费
	private String pic;//艺人的头像
	private String city;
	private String performanceType;
	/**
	 * @return the performanceType
	 */
	public String getPerformanceType() {
		return performanceType;
	}

	/**
	 * @param performanceType the performanceType to set
	 */
	public void setPerformanceType(String performanceType) {
		this.performanceType = performanceType;
	}

	

	public String getId() {
		return id;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
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

	public boolean isV() {
		return isV;
	}

	public void setV(boolean isV) {
		this.isV = isV;
	}

	public int getFanNum() {
		return fanNum;
	}

	public void setFanNum(int fanNum) {
		this.fanNum = fanNum;
	}

	public int getCompleteNum() {
		return completeNum;
	}

	public void setCompleteNum(int completeNum) {
		this.completeNum = completeNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
	}

	public String getSpecialtys() {
		return specialtys;
	}

	public void setSpecialtys(String specialtys) {
		this.specialtys = specialtys;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
