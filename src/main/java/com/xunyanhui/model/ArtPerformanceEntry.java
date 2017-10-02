/**
 * 创建日期：2016-12-23下午9:38:37
 * 修改日期：
 * 作者：邢传军
 * 用于读取用户报名的演艺活动，与视图art_performance_entry对应
 */
package com.xunyanhui.model;

public class ArtPerformanceEntry {
	
	private String title;//演艺活动标题
	private String uid;//承担演艺活动的用户id
	private int state;//演艺活动的报名执行情况，0表示报名待成交，1表示成交待执行，2表示成交完成待支付，3表示成交支付完毕，4被取消
	private String begin_time;//演艺活动起始时间
	private String end_time;//演艺活动结束时间
	private String days;//演艺活动天数
	private float salary;//演艺活动薪酬说明
	private float price;//演艺活动的报名价格/成交价格
	private int signup_num;//演艺活动报名人数
	private int signup_state;//演艺活动是否允许报名
	private String perid;//演艺活动id
	private String city;//演艺活动的所在城市
	private String type;//演艺类别，使用1-16的编码形式
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
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
	/**
	 * @return the begin_time
	 */
	public String getBegin_time() {
		return begin_time;
	}
	/**
	 * @param begin_time the begin_time to set
	 */
	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}
	/**
	 * @return the end_time
	 */
	public String getEnd_time() {
		return end_time;
	}
	/**
	 * @param end_time the end_time to set
	 */
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	/**
	 * @return the salary
	 */
	public float getSalary() {
		return salary;
	}
	/**
	 * @param salary the salary to set
	 */
	public void setSalary(float salary) {
		this.salary = salary;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * @return the signup_num
	 */
	public int getSignup_num() {
		return signup_num;
	}
	/**
	 * @param signup_num the signup_num to set
	 */
	public void setSignup_num(int signup_num) {
		this.signup_num = signup_num;
	}
	/**
	 * @return the signup_state
	 */
	public int getSignup_state() {
		return signup_state;
	}
	/**
	 * @param signup_state the signup_state to set
	 */
	public void setSignup_state(int signup_state) {
		this.signup_state = signup_state;
	}
	/**
	 * @return the perid
	 */
	public String getPerid() {
		return perid;
	}
	/**
	 * @param perid the perid to set
	 */
	public void setPerid(String perid) {
		this.perid = perid;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
