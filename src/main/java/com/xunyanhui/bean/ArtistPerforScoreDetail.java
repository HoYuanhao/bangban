package com.xunyanhui.bean;

import java.util.List;

import com.xunyanhui.model.ArtistPerforEvaluate;

/*
 * 艺人得到的演艺活动评价的详细信息
 */
public class ArtistPerforScoreDetail {
	
	private List<ArtistPerforEvaluate> scoreList;//艺人对指定演艺活动得到的打分值得详细列表
	private String evaluation;//评论值
	private int isGood;//是否评论，1评论过，0未评论
	private int goodLevel;//评论过1好评，0差评,2未知
	public List<ArtistPerforEvaluate> getScoreList() {
		return scoreList;
	}
	public void setScoreList(List<ArtistPerforEvaluate> scoreList) {
		this.scoreList = scoreList;
	}
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	public int getIsGood() {
		return isGood;
	}
	public void setIsGood(int isGood) {
		this.isGood = isGood;
	}
	public int getGoodLevel() {
		return goodLevel;
	}
	public void setGoodLevel(int goodLevel) {
		this.goodLevel = goodLevel;
	}
	
	
}
