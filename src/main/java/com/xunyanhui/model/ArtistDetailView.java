/*
 * 时间：2016-12-17 14:46
 * 作者：邢传军
 * 目的：用于app中艺人详情的中的展示数据
 * 根据柯鑫创建的实体模型更改而来
 */
package com.xunyanhui.model;

import java.math.BigDecimal;
import java.util.List;

import com.xunyanhui.bean.ArtistAnnouncementV;

public class ArtistDetailView {

	private String id;
	private List<ArtistAnnouncementV> artistAnnouncementList;// 艺人通告
	private List<ArtistOpusV> artistOpusList;// 作品
	private List<ArtistEvaluate> artistEvaluateList;// 艺人评价汇总
	private List<ArtPerformanceEvaluation> aPEList;// 艺人评价汇总
	private UserAttentionV uAttention;//对艺人关注情况
	
	private String stageName;// 艺名
	private String performanceTypeId;// 演绎类型
	private String specialty;// 特长
	private int singularNum;// 接单数
	private int completeNum;// 完成数
	private int fanNum;// 粉丝数
	private float rateOfpraise;// 好评率
	private String birthday;// 生日
	private BigDecimal minSalary;// 最低出场费
	private String address;// 地址
	private String selfIntroduction;// 自我评价
	private String constellation;// 星座
	private String bloodType;// 血型
	private int stature;// 身高
	private float weight;// 体重
	private String bwh;// 三围
	private String graduateSchool;// 毕业学校
	private String educationalBackground;// 学历
	private int artisticCareer;// 从业年限
	private boolean isAcceptInvitation;// 是否接单
	private boolean isBanned;// 是否停号
	private String homepic;//封面图片，头像
	private String performanceList;//演出经历
	private String biographyList;//获奖经历
	private String biographyHighest;//最高奖项
	private int isAuth;//是否实名认证
	private int honestyLevel;//诚信等级
	private String pic;//艺人头像
	private int sex;//艺人性别
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ArtistAnnouncementV> getArtistAnnouncementList() {
		return artistAnnouncementList;
	}

	public void setArtistAnnouncementList(
			List<ArtistAnnouncementV> artistAnnouncementList) {
		this.artistAnnouncementList = artistAnnouncementList;
	}

	public List<ArtistOpusV> getArtistOpusList() {
		return artistOpusList;
	}

	public void setArtistOpusList(List<ArtistOpusV> artistOpusList) {
		this.artistOpusList = artistOpusList;
	}

	

	public List<ArtistEvaluate> getArtistEvaluateList() {
		return artistEvaluateList;
	}

	public void setArtistEvaluateList(List<ArtistEvaluate> artistEvaluateList) {
		this.artistEvaluateList = artistEvaluateList;
	}



	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getPerformanceTypeId() {
		return performanceTypeId;
	}

	public void setPerformanceTypeId(String performanceTypeId) {
		this.performanceTypeId = performanceTypeId;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public int getSingularNum() {
		return singularNum;
	}

	public void setSingularNum(int singularNum) {
		this.singularNum = singularNum;
	}

	public int getCompleteNum() {
		return completeNum;
	}

	public void setCompleteNum(int completeNum) {
		this.completeNum = completeNum;
	}

	public int getFanNum() {
		return fanNum;
	}

	public void setFanNum(int fanNum) {
		this.fanNum = fanNum;
	}

	public float getRateOfpraise() {
		return rateOfpraise;
	}

	public void setRateOfpraise(float rateOfpraise) {
		this.rateOfpraise = rateOfpraise;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public BigDecimal getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(BigDecimal minSalary) {
		this.minSalary = minSalary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSelfIntroduction() {
		return selfIntroduction;
	}

	public void setSelfIntroduction(String selfIntroduction) {
		this.selfIntroduction = selfIntroduction;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public int getStature() {
		return stature;
	}

	public void setStature(int stature) {
		this.stature = stature;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getBwh() {
		return bwh;
	}

	public void setBwh(String bwh) {
		this.bwh = bwh;
	}

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public String getEducationalBackground() {
		return educationalBackground;
	}

	public void setEducationalBackground(String educationalBackground) {
		this.educationalBackground = educationalBackground;
	}

	public int getArtisticCareer() {
		return artisticCareer;
	}

	public void setArtisticCareer(int artisticCareer) {
		this.artisticCareer = artisticCareer;
	}

	public boolean isAcceptInvitation() {
		return isAcceptInvitation;
	}

	public void setAcceptInvitation(boolean isAcceptInvitation) {
		this.isAcceptInvitation = isAcceptInvitation;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", artistAnnouncementList="
				+ artistAnnouncementList + ", artistOpuList=" + artistOpusList
				
				+ ", artistEvaluateList=" + artistEvaluateList
				+ ", stageName="
				+ stageName + ", performanceTypeId=" + performanceTypeId
				+ ", specialty=" + specialty + ", singularNum=" + singularNum
				+ ", completeNum=" + completeNum + ", fanNum=" + fanNum
				+ ", rateOfpraise=" + rateOfpraise + ", birthday=" + birthday
				+ ", minSalary=" + minSalary + ", address=" + address
				+ ", selfIntroduction=" + selfIntroduction + ", constellation="
				+ constellation + ", bloodType=" + bloodType + ", stature="
				+ stature + ", weight=" + weight + ", bwh=" + bwh
				+ ", graduateSchool=" + graduateSchool
				+ ", educationalBackground=" + educationalBackground
				+ ", artisticCareer=" + artisticCareer
				+ ", isAcceptInvitation=" + isAcceptInvitation + ", isBanned="
				+ isBanned + "]";
	}

	public String getHomepic() {
		return homepic;
	}

	public void setHomepic(String homepic) {
		this.homepic = homepic;
	}

	public String getPerformanceList() {
		return performanceList;
	}

	public void setPerformanceList(String performanceList) {
		this.performanceList = performanceList;
	}

	public String getBiographyList() {
		return biographyList;
	}

	public void setBiographyList(String biographyList) {
		this.biographyList = biographyList;
	}

	public String getBiographyHighest() {
		return biographyHighest;
	}

	public void setBiographyHighest(String biographyHighest) {
		this.biographyHighest = biographyHighest;
	}

	public int getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(int isAuth) {
		this.isAuth = isAuth;
	}

	public int getHonestyLevel() {
		return honestyLevel;
	}

	public void setHonestyLevel(int honestyLevel) {
		this.honestyLevel = honestyLevel;
	}

	public List<ArtPerformanceEvaluation> getaPEList() {
		return aPEList;
	}

	public void setaPEList(List<ArtPerformanceEvaluation> aPEList) {
		this.aPEList = aPEList;
	}

	public UserAttentionV getuAttention() {
		return uAttention;
	}

	public void setuAttention(UserAttentionV uAttention) {
		this.uAttention = uAttention;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	

}
