package com.xunyanhui.bean;

/*
 * 表示用户的实名认证数据
 */
public class AuthState {
	
	private int type;// 证件类型
	private String authName;// 认证名
	private String idNum;// 证件号
	private String auditresult;//审核结果
	

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

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getAuditresult() {
		return auditresult;
	}

	public void setAuditresult(String auditresult) {
		this.auditresult = auditresult;
	}

	

}
