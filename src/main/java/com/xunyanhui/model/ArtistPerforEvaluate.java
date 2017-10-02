package com.xunyanhui.model;

/*
 * 艺人完成演艺活动后接受的评价数据
 */
public class ArtistPerforEvaluate {
	private String id;
	private String uid;// 被评价艺人id
	private String evaluateId;// 评价人id
	private int type;// 评价类型
	private int value;// 评价值
	private String performanceId;//演艺活动id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	@Override
	public String toString() {
		return "ArtistEvaluate [id=" + id + ", uid=" + uid + ", evaluateId="
				+ evaluateId + ", type=" + type + ", value=" + value + "]";
	}

	public String getPerformanceId() {
		return performanceId;
	}

	public void setPerformanceId(String performanceId) {
		this.performanceId = performanceId;
	}

}
