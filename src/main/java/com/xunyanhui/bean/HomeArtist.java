package com.xunyanhui.bean;

public class HomeArtist {
	private String id;
	private String stageName;
	private String specialty;
	private int fanNum;
	private String address;
	private int completeNum;
	private String minSalary;
	private String pic;//艺人头像
	private int distance;//距离用户距离

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

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

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public int getFanNum() {
		return fanNum;
	}

	public String getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(String minSalary) {
		this.minSalary = minSalary;
	}

	public void setFanNum(int fanNum) {
		this.fanNum = fanNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCompleteNum() {
		return completeNum;
	}

	public void setCompleteNum(int completeNum) {
		this.completeNum = completeNum;
	}

	
	@Override
	public String toString() {
		return "HomeArtist [id=" + id + ", stageName=" + stageName + ", specialty=" + specialty + ", fanNum=" + fanNum
				+ ", address=" + address + ", completeNum=" + completeNum + ", minSalary=" + minSalary + ", pic=" + pic
				+ ", distance=" + distance + "]";
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}
