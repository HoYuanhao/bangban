package com.xunyanhui.bean;

/*
 * 用于用户登录后获取用户的简要信息
 */
public class UserInfoSimple {
	private String userName;//用户名称
	private String nickName;//用户昵称
	private String pic;//用户头像的扩展名
	private String imgId;//艺人的艺名
	private int isrecommand;//艺人的当前信息状态

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getImgId() {
		return imgId;
	}
	public void setImgId(String imgId) {
		this.imgId = imgId;
	}
	
	public int getIsrecommand() {
		return isrecommand;
	}
	
	public void setIsrecommand(int isrecommand) {
		this.isrecommand = isrecommand;
	}
	

}
