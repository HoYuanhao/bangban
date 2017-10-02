package com.xunyanhui.model;


import java.util.Date;

/*
 * 评价类型
 */
public class EvaluationDetails {

	private String id;
	private String description;// 类型描述
	private String typename;// 类型名,当前未使用
	private int evaluateType;// 评价对象类型，描述对象类型，1表示艺人，2表示作品或小样，3表示演艺，其他待扩充
	private String releaseid;//评价人id
	private Date releasetime;//评价时间
	private String acceptid;//被评价人id
	private String objectid;//被评价对象id，与evaluatetype相关，若evaluatetype为：1，则objectid表示艺人id，2表示作品或小样id，3表示演艺id，其他待扩充
	private int evalevel;//好评或差评或是点赞，1表示好评或是点赞，0表示差评或是取消点赞 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public int getEvaluateType() {
		return evaluateType;
	}

	public void setEvaluateType(int evaluateType) {
		this.evaluateType = evaluateType;
	}

	@Override
	public String toString() {
		return "EvaluationDetails [id=" + id + ", description=" + description
				+ ", typename=" + typename + ", evaluateType=" + evaluateType
				+ "]";
	}

	public String getReleaseid() {
		return releaseid;
	}

	public void setReleaseid(String releaseid) {
		this.releaseid = releaseid;
	}

	public Date getReleasetime() {
		return releasetime;
	}

	public void setReleasetime(Date releasetime) {
		this.releasetime = releasetime;
	}

	public String getAcceptid() {
		return acceptid;
	}

	public void setAcceptid(String acceptid) {
		this.acceptid = acceptid;
	}

	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}

	public int getEvalevel() {
		return evalevel;
	}

	public void setEvalevel(int evalevel) {
		this.evalevel = evalevel;
	}

}
