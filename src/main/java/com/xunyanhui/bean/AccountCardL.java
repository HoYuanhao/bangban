/**
 * 创建日期：2017-1-14上午10:22:13
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.bean;



public class AccountCardL {
	
	private String accountCardId;
	private String cardNo;
	private String bank;
	private int isMaster;
	private String cardType;

	public String getAccountCardId() {
		return accountCardId;
	}
	public void setAccountCardId(String accountCardId) {
		this.accountCardId = accountCardId;
	}
	
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	public int getIsMaster() {
		return isMaster;
	}
	public void setIsMaster(int isMaster) {
		this.isMaster = isMaster;
	}

	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	

}
