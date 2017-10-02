/**
 * 创建日期：2017-1-13下午2:48:08
 * 修改日期：
 * 作者：邢传军
 * 用于返回给客户端的模拟字符串
 * 
 */
package com.xunyanhui.bean;

public class OrderReturn {
	
	private String service;//接口名称
	private String partner;//appid（支付宝分配给开发者的应用id
	private String _input_chaerset;//请求使用的编码格式
	private String out_trade_no;//商户网站的唯一订单号
	private String subject;//商品的标题/交易标题/订单标题/订单关键字
	private String payment_type;
	private String seller_id;//收款支付宝用户id
	private String totla_fee;//订单总金额，单位是元
	private String body;//订单描述
	private String it_b_pay;//特殊的关键字
	private String notify_url;//支付宝服务器主动通知商户服务器指定的页面http/https
	private String show_url;//
	private String sign;//商户请求参数的签名串(密钥)
	private String sign_type;//签名类型
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String get_input_chaerset() {
		return _input_chaerset;
	}
	public void set_input_chaerset(String _input_chaerset) {
		this._input_chaerset = _input_chaerset;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getTotla_fee() {
		return totla_fee;
	}
	public void setTotla_fee(String totla_fee) {
		this.totla_fee = totla_fee;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getIt_b_pay() {
		return it_b_pay;
	}
	public void setIt_b_pay(String it_b_pay) {
		this.it_b_pay = it_b_pay;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getShow_url() {
		return show_url;
	}
	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	
	

}
