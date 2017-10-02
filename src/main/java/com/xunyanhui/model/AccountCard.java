/**
 * 创建日期：2017-1-14上午10:22:13
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.model;

import java.util.Date;

public class AccountCard {
	
	private String accountCardId;
	private String uid;
	private String cardNo;
	private String bank;
	private Date bandTime;
	private int isMaster;
	private String cardName;
	private String cardtype;
	private String accound_id;
	
	public String getAccountCardId() {
		return accountCardId;
	}
	public void setAccountCardId(String accountCardId) {
		this.accountCardId = accountCardId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public Date getBandTime() {
		return bandTime;
	}
	public void setBandTime(Date bandTime) {
		this.bandTime = bandTime;
	}
	public int getIsMaster() {
		return isMaster;
	}
	public void setIsMaster(int isMaster) {
		this.isMaster = isMaster;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardtype() {
		return cardtype;
	}
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	public String getAccound_id() {
		return accound_id;
	}
	public void setAccound_id(String accound_id) {
		this.accound_id = accound_id;
	}

}
