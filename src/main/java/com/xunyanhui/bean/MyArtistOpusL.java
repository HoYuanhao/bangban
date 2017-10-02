package com.xunyanhui.bean;

import java.util.List;
/*
 * 用于app中我的中小样或作品的详情显示
 */

public class MyArtistOpusL {
	private String oid;//小样的id
	private String name;// 作品名称
	private int playNum;// 点击数
	private int praiseNum;// 好评数
	private String pic;
	private String picPath;
	private String label;
	private String kind;
	private String description;//小样或作品的描述
	private String type;
	private String fileName;//小样或作品对应的文件
	private List<EvaluationOpus> evaluationOpusList;//小样或作品详情中评价列表
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public int getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

	public int getPlayNum() {
		return playNum;
	}

	public void setPlayNum(int playNum) {
		this.playNum = playNum;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EvaluationOpus> getEvaluationOpusList() {
		return evaluationOpusList;
	}

	public void setEvaluationOpusList(List<EvaluationOpus> evaluationOpusList) {
		this.evaluationOpusList = evaluationOpusList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

}
