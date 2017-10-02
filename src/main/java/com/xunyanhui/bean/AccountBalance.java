/**
 * 创建日期：2017-1-11下午3:45:26
 * 修改日期：
 * 作者：邢传军
 * 用户的账户信息
 */
package com.xunyanhui.bean;

public class AccountBalance {
	private String uid;
	private Double money;
	private String passWord;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
