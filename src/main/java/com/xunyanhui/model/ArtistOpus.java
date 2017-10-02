package com.xunyanhui.model;

import java.util.Date;

public class ArtistOpus {
	private String id;
	private String oid;//小样的id
	private String uid;//小样或作品所属的uid
	private String name;// 作品名称
	private String description;// 作品描述
	private Date releaseTime;// 发布时间
	private String releaseId;// 发布id
	private int type;// 作品类型
	private String fileName;// 文件名
	private String filePath;// 文件路径
	private int clickNum;// 点击数
	private int praiseNum;// 好评数

	
	private int playNum;// 点击数
	private String pic;
	private String picPath;
	private String homePic;
	private String homePicPath;
	private String label;
	private String kind;

	
public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}

	
	public int getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(int praiseNum) {
		this.praiseNum = praiseNum;
	}

	@Override
	public String toString() {
		return "ArtistOpus [id=" + id + ", name=" + name + ", description="
				+ description + ", releaseTime=" + releaseTime + ", releaseId="
				+ releaseId + ", type=" + type + ", fileName=" + fileName
				+ ", filePath=" + filePath + ", clickNum=" + clickNum
				+ ", praiseNum=" + praiseNum + "]";
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

	public String getHomePic() {
		return homePic;
	}

	public void setHomePic(String homePic) {
		this.homePic = homePic;
	}

	public String getHomePicPath() {
		return homePicPath;
	}

	public void setHomePicPath(String homePicPath) {
		this.homePicPath = homePicPath;
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	

}
