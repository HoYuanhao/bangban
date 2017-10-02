package com.xunyanhui.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import java.util.List;

public class User {
	private String id;// 主键
	private String mobile;
	private Artist artist;
	private Employer employer;

	private List<Coupon> couponList; // 卡卷列表

	private int gender;

	private String userName;// 用户名
	private String password;// 密码
	private String nickName;// 平台昵称
	private Date registerTime;// 注册时间
	private boolean isAuth;// 是否实名
	private Date authTime;// 实名时间
	private int authState;// 是否实名
	
	private boolean v;
	private String preIp;// 最后登录ip
	private int grade;// 用户等级
	private String preAddress;// 最后登录地理位置
	private String lastActiveId;// 最后活动id
	private String lastInfomationId;// 最后消息id

	
	private String cid;// 内部个推的id
	private boolean isForbLogin;// 时候禁止登录 1 不禁止登录 0 禁止登录
	private String lastapid;// 最后演艺Id

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public List<Coupon> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<Coupon> couponList) {
		this.couponList = couponList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public boolean isAuth() {
		return isAuth;
	}

	public void setAuth(boolean isAuth) {
		this.isAuth = isAuth;
	}
	public int getAuthState() {
		return authState;
	}

	public void setAuthState(int authState) {
		this.authState = authState;
	}

	public Date getAuthTime() {
		return authTime;
	}

	public void setAuthTime(Date authTime) {
		this.authTime = authTime;
	}

	public boolean isV() {
		return v;
	}

	public void setV(boolean v) {
		this.v = v;
	}

	public String getPreIp() {
		return preIp;
	}

	public void setPreIp(String preIp) {
		this.preIp = preIp;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getPreAddress() {
		return preAddress;
	}

	public void setPreAddress(String preAddress) {
		this.preAddress = preAddress;
	}

	public String getLastActiveId() {
		return lastActiveId;
	}

	public void setLastActiveId(String lastActiveId) {
		this.lastActiveId = lastActiveId;
	}

	public String getLastInfomationId() {
		return lastInfomationId;
	}

	public void setLastInfomationId(String lastInfomationId) {
		this.lastInfomationId = lastInfomationId;
	}

	public boolean isForbLogin() {
		return isForbLogin;
	}

	public void setForbLogin(boolean isForbLogin) {
		this.isForbLogin = isForbLogin;
	}

	public String getLastapid() {
		return lastapid;
	}

	public void setLastapid(String lastapid) {
		this.lastapid = lastapid;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", artist=" + artist + ", employer="
				+ employer + ", couponList=" + couponList + ", userName="
				+ userName + ", password=" + password + ", nickName="
				+ nickName + ", registerTime=" + registerTime + ", authState="
				+ authState + ", authTime=" + authTime + ", v=" + v
				+ ", preIp=" + preIp + ", grade=" + grade + ", preAddress="
				+ preAddress + ", lastActiveId=" + lastActiveId
				+ ", lastInfomationId=" + lastInfomationId + ", isForbLogin="
				+ isForbLogin + ", lastapid=" + lastapid + "]";
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

}
