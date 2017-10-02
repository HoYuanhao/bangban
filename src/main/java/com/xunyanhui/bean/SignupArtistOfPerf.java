package com.xunyanhui.bean;

/*
 * 用于显示报名某一用户发布的演艺的报名艺人列表
 */
public class SignupArtistOfPerf {
	private String artistid;//艺人id
	private String description;//报名的详细说明
	private Integer price;//报名的报价
	private String stagename;//艺人的艺名
	private String specialty;//
	private int state;//
	private String pic;//艺人头像
	/**
	 * @return the artistid
	 */
	public String getArtistid() {
		return artistid;
	}
	/**
	 * @param artistid the artistid to set
	 */
	public void setArtistid(String artistid) {
		this.artistid = artistid;
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
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * @return the stagename
	 */
	public String getStagename() {
		return stagename;
	}
	/**
	 * @param stagename the stagename to set
	 */
	public void setStagename(String stagename) {
		this.stagename = stagename;
	}
	/**
	 * @return the specialty
	 */
	public String getSpecialty() {
		return specialty;
	}
	/**
	 * @param specialty the specialty to set
	 */
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
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
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	

}
