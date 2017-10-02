package com.xunyanhui.jms.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;



/**
 * The persistent class for the T_OA_TEAM database table.
 * 
 */

public class MessageInfo implements Serializable {

	
	/**
	 * 用于生成在jms服务之间发送的消息
	 */
	private static final long serialVersionUID = 5845584896087019962L;
	private int type;//消息的类型
	private String sid;//消息发送者
	private String did;//消息的接收者
	private String value;//消息的值
	private String option;//消息的附加参数
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	
	
}