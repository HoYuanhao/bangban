package com.xunyanhui.bean;


/**
 * @author Administrator
 * 是将Perfean添加了path用来表示图片的base64的字符串
 * 
 */
public class PerfBeanImg {
	
	private PerfBean perfBean;
	private String path;//图片头像base64串
	public PerfBean getPerfBean() {
		return perfBean;
	}
	public void setPerfBean(PerfBean perfBean) {
		this.perfBean = perfBean;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	

}
