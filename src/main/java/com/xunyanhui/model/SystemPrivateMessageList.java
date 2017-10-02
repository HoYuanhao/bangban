package com.xunyanhui.model;

import java.util.Date;

/*
 * 系统内私信,用于传送指定用户接收和发送的私信列表
 */
public class SystemPrivateMessageList {
	private String acceptId;//接受人id
	private String releaseId;// 发布人id
	private String releaseTime;// 发布人时间
	private String details;// 消息详情
	private boolean isRead;// 是否读取
	private String sName;//发送人的用户名
	private String cId;//对应用户的cid
	private String pic;//对应留言人的图像文件后缀
	

	public String getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}

	

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	@Override
	public String toString() {
		return "SystemPrivateMessageList [releaseId=" + releaseId
				+ ", releaseTime=" + releaseTime
				+ ", details=" + details + ", isRead=" + isRead + "]";
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getAcceptId() {
		return acceptId;
	}

	public void setAcceptId(String acceptId) {
		this.acceptId = acceptId;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	

}
