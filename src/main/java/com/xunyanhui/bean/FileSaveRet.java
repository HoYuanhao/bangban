/**
 * 创建日期：2017-1-8下午8:01:41
 * 修改日期：
 * 作者：邢传军
 * 目的：文件保存结果返回值
 */
package com.xunyanhui.bean;

public class FileSaveRet {
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileSaveRet [code=" + code + ", pic_extra=" + pic_extra
				+ ", msg=" + msg + "]";
	}
	private int code;
	private String pic_extra;
	private String msg;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getPic_extra() {
		return pic_extra;
	}
	public void setPic_extra(String pic_extra) {
		this.pic_extra = pic_extra;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
