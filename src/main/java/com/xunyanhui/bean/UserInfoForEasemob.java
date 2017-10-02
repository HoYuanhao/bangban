package com.xunyanhui.bean;

/*
 * 用于批量获取用户信息供添加环信用户
 */
public class UserInfoForEasemob {
	private String id;//用户id
	private String nickName;//用户昵称
	private String password;//环信应用密码
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
