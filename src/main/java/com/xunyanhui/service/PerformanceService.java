package com.xunyanhui.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xunyanhui.bean.PerfBean;
import com.xunyanhui.bean.PerforBean;
import com.xunyanhui.bean.Performance;
import com.xunyanhui.model.EntryRecordN;
import com.xunyanhui.model.User;

public interface PerformanceService {
	public final static int NORMAL = 0; // 正常
	public final static int SALARY_DESC = 1;// 价格从高到低
	public final static int SALARY_ASC = 2;// 价格从低到高
	public final static int POPULARITY_DESC = 3;// 人气从高到低
	public final static int POPULARITY_ASC = 4;// 人气从低到高

	public final static int IN_REVIEW = 1;// 审核中
	public final static int AUDIT_PASS = 2;// 审核通过
	public final static int IN_TREATMENT = 3;// 投诉处理中
	public final static int END = 4;// 下架

	public List<PerforBean> getPerformanceList(Date date, int order, String type, int page, String uid,String ccity);

	public boolean addPerformance(PerfBean perfBean, String pid, String pic);

	public Performance getPerformance(String perforId, HttpServletRequest request);

	/*
	 * 用户报名
	 */
	public int performanceSignup(User user, String peformanceId, int price, String desc);

	/*
	 * 用户邀请报名
	 */
	public int performanceInvite(String artistid, String performanceId, String userid);

	/*
	 * 用户接受邀请报名
	 */
	public int enableInvite(String artistid, String performanceId, int price, String desc);

	public List<PerforBean> searchByKey(Date date, String key, int page, String uid,String ccity);

	/**
	 * 获取指定艺人的指定演艺活动的报名情况的详细内容
	 * 
	 * @param peformanceId
	 *            演艺id
	 * @param artistId
	 *            艺人id
	 */
	public EntryRecordN getEntryRecord(String artistId, String performanceId);

}
