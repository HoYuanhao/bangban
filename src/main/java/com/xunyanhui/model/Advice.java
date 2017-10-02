/**
 * 创建日期：2017-3-3下午3:45:26
 * 修改日期：
 * 作者：邢传军
 * 用户的反馈与建议
 */
package com.xunyanhui.model;

import java.util.Date;

public class Advice {
	
	private int advid;//
	private String uid;
	private String content;
	private Date advicetime;
	private String result;
	public int getAdvid() {
		return advid;
	}
	public void setAdvid(int advid) {
		this.advid = advid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getAdvicetime() {
		return advicetime;
	}
	public void setAdvicetime(Date advicetime) {
		this.advicetime = advicetime;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
