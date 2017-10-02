/**
 * 创建日期：2017-1-13下午3:37:34
 * 修改日期：
 * 作者：邢传军
 * 用户发起充值请求后,系统生成订单应当返回给app的订单信息
 */
package com.xunyanhui.bean;

import java.util.Map;

public class BeeCloudMessage {
	private String app_id;//App在BeeCloud平台的唯一标识
	private Long timestamp;//签名生成时间,时间戳，毫秒数
	private String app_sign;//加密签名,算法: md5(app_id+timestamp+app_secret)，32位16进制格式,不区分大小写
	private String channel;//渠道类型,
	/*
	 * 根据不同场景选择不同的支付方式,
	 * WX_APP、WX_NATIVE、WX_JSAPI、ALI_APP、ALI_WEB、ALI_QRCODE、ALI_OFFLINE_QRCODE、
	 * ALI_WAP、UN_APP、UN_WEB、UN_WAP、PAYPAL_SANDBOX、PAYPAL_LIVE、JD_WAP、
	 * JD_WEB、YEE_WAP、YEE_WEB、YEE_NOBANKCARD、KUAIQIAN_WAP、KUAIQIAN_WEB、
	 * BD_APP、BD_WEB、BD_WAP、BC_GATEWAY、BC_EXPRESS、BC_APP、BC_NATIVE、
	 * BC_WX_WAP、BC_WX_JSAPI、BC_ALI_QRCODE（详见附注
	 */
	private Integer total_fee;//订单总金额,必须是正整数，单位为分
	private String bill_no;//商户订单号,8到32位数字和/或字母组合，请自行确保在商户系统中唯一，同一订单号不可重复提交，否则会造成订单重复
	private String title;//订单标题,UTF8编码格式，32个字节内，最长支持16个汉字
	private Map<String,String> optional;//附加数据,用户自定义的参数，
	/*
	 * 将会在webhook通知中原样返回，该字段主要用于商户携带订单的自定义数据,{"key1”:“value1”,“key2”:“value2”,…}
	 */
	private Map<String,String> analysis;//分析数据,
	/*
	 * 用于统计分析的数据，将会在控制台的统计分析报表中展示，用户自愿上传,
	 * 包括以下基本字段：os_name(系统名称，如"iOS"，"Android") 
	 * os_version(系统版本，如"5.1") model(手机型号，如"iPhone 6") 
	 * app_name(应用名称) app_version(应用版本号) device_id(设备ID) 
	 * category(类别，用户可自定义，如游戏分发渠道，门店ID等) browser_name(浏览器名称) browser_version(浏览器版本)
	 */
	private String return_url;//同步返回页面,
	/*
	 * 支付渠道处理完请求后,当前页面自动跳转到商户网站里指定页面的http路径，
	 * 中间请勿有#,?等字符当channel参数为 ALI_WEB 或 ALI_QRCODE 或 UN_WEB 或 JD_WAP 或 JD_WEB时为必填
	 */
	private String notify_url;//商户自定义回调地址,
	/*
	 * 商户可通过此参数设定回调地址，此地址会覆盖用户在控制台设置的回调地址。必须以http://或https://开头,
	 */
	private Integer bill_timeout;//订单失效时间,必须为非零正整数，单位为秒，建议最短失效时间间隔必须大于360秒,否, 京东(JD)不支持该参数。
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getApp_sign() {
		return app_sign;
	}
	public void setApp_sign(String app_sign) {
		this.app_sign = app_sign;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Integer getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}
	public String getBill_no() {
		return bill_no;
	}
	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Map<String, String> getOptional() {
		return optional;
	}
	public void setOptional(Map<String, String> optional) {
		this.optional = optional;
	}
	public Map<String, String> getAnalysis() {
		return analysis;
	}
	public void setAnalysis(Map<String, String> analysis) {
		this.analysis = analysis;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public Integer getBill_timeout() {
		return bill_timeout;
	}
	public void setBill_timeout(Integer bill_timeout) {
		this.bill_timeout = bill_timeout;
	}
	

}
