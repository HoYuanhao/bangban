package com.xunyanhui.model;

import java.util.Date;

/*
 * 认证文件
 */
public class AuthFile {
	private String id;
	private String uid;// 认证的id
	private int type;// 证件类型
	private String authName;// 认证名
	private int idNum;// 证件号
	private String fileName;// 文件名
	private String path;// 文件路径
	private Date time;// 认证时间
	private int state;// 审核状态
	private String auditid;//审核人id
	private Date audittime;//审核时间
	private String auditresult;//审核结果
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public int getIdNum() {
		return idNum;
	}

	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "AuthFile [id=" + id + ", uid=" + uid + ", type=" + type
				+ ", authName=" + authName + ", idNum=" + idNum + ", fileName="
				+ fileName + ", path=" + path + ", time=" + time + ", state="
				+ state + "]";
	}

	


	public String getAuditid() {
		return auditid;
	}

	public void setAuditid(String auditid) {
		this.auditid = auditid;
	}

	public Date getAudittime() {
		return audittime;
	}

	public void setAudittime(Date audittime) {
		this.audittime = audittime;
	}

	public String getAuditresult() {
		return auditresult;
	}

	public void setAuditresult(String auditresult) {
		this.auditresult = auditresult;
	}

	

}
