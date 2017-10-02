/**
 * 创建日期：2017-1-15下午3:49:26
 * 修改日期：
 * 作者：邢传军
 * 用于表示账单的详细信息
 */
package com.xunyanhui.bean;

public class Bill {
	private String trid;//交易id
	private float money;//交易金额
	private String description;//交易描述
	private int type;//交易类型，1表示充值，2表示取现,3表示支付，4表示退款
	private int state;//交易状态，1：发起，2：支付方审核，3：支付方处理；4：平台审核，5：平台处理，6：收款方审核，7收款方处理，8状态完毕
	private String ordertime;//交易的详细时间
	private String paymentid;//交易的支付方id
	private String pu;//交易的支付方名字
	private String ppic;//支付方的头像文件扩展名
	private String receiveid;//交易的接收方id
	private String ru;//交易的接收方名字
	private String rpic;//接收方的头像文件扩展名
	private String yearmonth;//交易发生的年月
	private String performanceid;//交易关联的演艺活动id
	private String title;//交易管理的演艺活动的标题
	public String getTrid() {
		return trid;
	}
	public void setTrid(String trid) {
		this.trid = trid;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public String getPaymentid() {
		return paymentid;
	}
	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}
	public String getPu() {
		return pu;
	}
	public void setPu(String pu) {
		this.pu = pu;
	}
	public String getReceiveid() {
		return receiveid;
	}
	public void setReceiveid(String receiveid) {
		this.receiveid = receiveid;
	}
	public String getRu() {
		return ru;
	}
	public void setRu(String ru) {
		this.ru = ru;
	}
	public String getYearmonth() {
		return yearmonth;
	}
	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}
	public String getPerformanceid() {
		return performanceid;
	}
	public void setPerformanceid(String performanceid) {
		this.performanceid = performanceid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPpic() {
		return ppic;
	}
	public void setPpic(String ppic) {
		this.ppic = ppic;
	}
	public String getRpic() {
		return rpic;
	}
	public void setRpic(String rpic) {
		this.rpic = rpic;
	}
	

}
