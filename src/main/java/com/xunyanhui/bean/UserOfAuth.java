/**
 * 创建日期：2017-1-11下午3:45:26
 * 修改日期：
 * 作者：邢传军
 * 用户的实名状态和手机号码
 */
package com.xunyanhui.bean;

public class UserOfAuth {
	private String uid;
	private String mobile;
	private int authstate;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getAuthstate() {
		return authstate;
	}
	public void setAuthstate(int authstate) {
		this.authstate = authstate;
	}
	
}
