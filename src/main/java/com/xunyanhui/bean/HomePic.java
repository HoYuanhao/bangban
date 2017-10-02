package com.xunyanhui.bean;

public class HomePic {
	private String id;
	private String picPath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	@Override
	public String toString() {
		return "HomePic [id=" + id + ", picPath=" + picPath + "]";
	}

}
