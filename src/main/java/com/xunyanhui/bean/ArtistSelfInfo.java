package com.xunyanhui.bean;

import java.math.BigDecimal;

public class ArtistSelfInfo {

	private String id;
	private String stageName;// 艺名
	private String performanceTypeId;// 艺人类型，不同类型之间用-分隔
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
	private String city;
	private int age;
	private String bwh;// 三围
	private String graduateSchool;// 毕业学校
	private String educationalBackground;// 学历
	private int artisticCareer;// 从业年限
	private boolean isAcceptInvitation;// 是否接单
	private String performanceList;
	private String biographyList;
	private String  biographyHighest;
	private int isAuth;
	private int honestyLevel;
	private boolean isBanned;// 是否停号
	private String pic;//艺人头像
	private String sex;//艺人性别

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
