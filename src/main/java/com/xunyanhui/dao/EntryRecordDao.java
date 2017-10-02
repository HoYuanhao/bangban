package com.xunyanhui.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xunyanhui.model.EntryRecord;
import com.xunyanhui.model.EntryRecordN;

public interface EntryRecordDao {
	/**
	 * 演艺报名
	 */
	public int performanceSignup(@Param("erid") String erid,
			@Param("artistId") String artistId,
			@Param("performanceId") String performanceId,
			@Param("price") int price,
			@Param("desc") String desc
			);

	/**
	 * 增加帮办中的报名人数
	 */
	public void addSignUpNumber(@Param("signup_num")String signup_num,
			@Param("id")String id);
	/**
	 * 是否报名
	 * 
	 * @param peformanceId
	 *            演艺id
	 * @param artistId
	 *            艺人id
	 * @return 1 已报名 0 末报名
	 */
	public int isSignup(@Param("peformanceId") String peformanceId,
			@Param("artistId") String artistId
			);

	public List<EntryRecord> signupList(String performanceId);
	
	/**
	 * 获取指定艺人的指定演艺活动的报名情况的详细内容
	 * @param peformanceId		演艺id
	 * @param artistId			艺人id
	 * 
	 */
	public EntryRecordN getEntryRecord(
			@Param("artistId") String artistId,
			@Param("performanceId") String performanceId
		
			);
	/**
	 * 演艺邀请报名
	 */
	public int performanceInvite(@Param("erid") String erid,
			@Param("artistId") String artistId,
			@Param("performanceId") String performanceId
			);

}
