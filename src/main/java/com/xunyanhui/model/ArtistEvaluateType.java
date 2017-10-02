package com.xunyanhui.model;

/*
 * 艺人可以接受的评价数据类型
 */
public class ArtistEvaluateType {
	private String id;
	private String evaluateId;// 评价人id
	private int type;// 评价类型
	private int value;// 评价值
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getEvaluateId() {
		return evaluateId;
	}

	public void setEvaluateId(String evaluateId) {
		this.evaluateId = evaluateId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	

	

}
