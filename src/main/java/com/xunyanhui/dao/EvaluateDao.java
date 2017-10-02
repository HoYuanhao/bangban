/**
 * 创建日期：2017-1-4下午12:53:08
 * 修改日期：
 * 作者：邢传军
 * 目的：评价处理相关
 */
package com.xunyanhui.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xunyanhui.model.ArtistEvaluateType;
import com.xunyanhui.model.ArtistPerforEvaluate;

public interface EvaluateDao {
	/**
	 * 用于判断指定的用户对指定的对象是否进行了点赞或好评
	 * @param releaseid				评论人id，该评论人的用户id相同
	 * @param objectid				被评价对象id
	 * @param evaluatetype			被评价对象的类型，1表示艺人，2表示作品或小样，3表示演艺，其他待扩充
	 * @return
	 */
	public Integer isGoodLevel(
			@Param("objectid") String objectid,
			@Param("releaseid") String releaseid,
			@Param("evaluatetype") int evaluatetype
			);
	/**
	 * 对指定的小样或作品/演艺/进行点赞或好评
	 * @param releaseid				评论人id，该评论人的用户id相同
	 * @param objectid				被评价对象id
	 * @param evaluatetype			被评价对象的类型，1表示艺人，2表示作品或小样，3表示演艺，其他待扩充
	 * @Param state					评价的状态值，1点赞/好评，0取消点赞/差评
	 * @return
	 */
	public Integer inGoodLevel(
			@Param("objectid") String objectid,
			@Param("releaseid") String releaseid,
			@Param("evaluatetype") int evaluatetype,
			@Param("state") int state
			);
	/**
	 * 对指定的小样或作品/演艺新建点赞或好评
	 * @param releaseid				评论人id，该评论人的用户id相同
	 * @param objectid				被评价对象id
	 * @param evaluatetype			被评价对象的类型，1表示艺人，2表示作品或小样，3表示演艺，其他待扩充
	 * @Param state					评价的状态值，1点赞/好评，0取消点赞/差评
	 * @return
	 */
	public Integer newGoodLevel(
			@Param("id") String id,
			@Param("objectid") String objectid,
			@Param("releaseid") String releaseid,
			@Param("evaluatetype") int evaluatetype,
			@Param("state") int state,
			@Param("acceptid") String acceptid
			);
	/**
	 * 取指定的用户指定的小样或作品/演艺的点赞和好评情况
	 * @param releaseid				评论人id，该评论人的用户id相同
	 * @param objectid				被评价对象id
	 * @param evaluatetype			被评价对象的类型，1表示艺人，2表示作品或小样，3表示演艺，其他待扩充
	 * @return
	 */
	public Integer getGoodLevel(
			@Param("objectid") String objectid,
			@Param("releaseid") String releaseid,
			@Param("evaluatetype") int evaluatetype
			);
	/**
	 * 读取指定的用户指定的艺人承担的指定演艺活动的好评情况
	 * @param releaseid				评论人id，该评论人的用户id相同
	 * @param objectid				被评价对象id
	 * @param evaluatetype			被评价对象的类型，1表示艺人，2表示作品或小样，3表示演艺，其他待扩充
	 * @param acceptid			被评价对象的类型，1表示艺人，2表示作品或小样，3表示演艺，其他待扩充
	 * @return
	 */
	public Integer getGoodLevelOfPerfor(
			@Param("objectid") String objectid,
			@Param("releaseid") String releaseid,
			@Param("evaluatetype") int evaluatetype,
			@Param("acceptid") String acceptid
			);
	/**
	 * 艺人完成演艺后是否接受到发布人的打分评价
	 * @param uid						被评论人id，
	 * @param performanceid				用户参加的演艺id
	 * @return
	 */
	public Integer isScore(
			@Param("uid") String uid,
			@Param("performanceid") String performanceid
			);
	/**
	 * 读取系统支持的用户可以打分评价项列表
	 * @return
	 */
	public List<ArtistEvaluateType> getScoreList();
	/**
	 * 读取艺人在指定演艺活动上得到的打分项
	 * @param artistid					艺人id，
	 * @param performanceid				用户参加的演艺id
	 * @return
	 */
	public List<ArtistPerforEvaluate> getScoreArtistOfPerfor(
			@Param("artistid") String artistid,
			@Param("performanceid") String performanceid
			);
	/**
	 * 读取艺人在指定演艺活动上得到的评论
	 * @param artistid					艺人id，
	 * @param performanceid				用户参加的演艺id
	 * @param uid						发布评论的用户id
	 * @return
	 */
	public String getCommentArtistOfPerfor(
			@Param("uid") String uid,
			@Param("artistid") String artistid,
			@Param("performanceid") String performanceid
			);
	/**
	 * 对指定的小样或作品/演艺新建评论
	 * @param releaseid				评论人id，该评论人的用户id相同
	 * @param objectid				被评价对象id
	 * @param evaluatetype			被评价对象的类型，1表示艺人，2表示作品或小样，3表示演艺，其他待扩充
	 * @Param description			评价的内容
	 * @Param artistid				被评价艺人id
	 * @return
	 */
	public Integer newCommentArtist(
			@Param("id") String id,
			@Param("objectid") String objectid,
			@Param("releaseid") String releaseid,
			@Param("evaluatetype") int evaluatetype,
			@Param("description") String description,
			@Param("artistid") String artistid
			
			);
	/**
	 * 写入艺人完成演艺后的打分值
	 * @param scoreList				打分评价的数据值
	 * @return
	 */
	public Integer addArtistPerforEvaluate(List<ArtistPerforEvaluate> scoreList);
	/**
	 * 读小样/作品的作者Id
	 * @param oid					作品或小样的id
	 * @return
	 */
	public String getOpusAuthor(
			@Param("oid") String oid
			);
	
	/**
	 * 修改小样的点赞数
	 * @param oid					小样的id
	 * @param uid					小样的作者id
	 * @param value					点赞的值，+1表示点赞数增加，-1表示点赞数减少
	 * @return
	 */
	public Integer updatePriseOfOpus(
			@Param("value") int value,
			@Param("oid") String oid,
			@Param("uid") String uid
			);
	/**
	 * 修改艺人的好评数，使他的好评数+1
	 * @param uid					艺人id
	 * @return
	 */
	public Integer updatePriseOfArtist(
			@Param("uid") String uid
			);
	/**
	 * 修改艺人好评率，用好评数与完成数据来计算好评率
	 * @param uid					艺人id
	 * @return
	 */
	public Integer updatePriseRateOfArtist(
			@Param("uid") String uid
	);
	/**
	 * 修改艺人的完成数
	 * @param uid					艺人id
	 * @return
	 */
	public Integer updateCompleteOfArtist(
			@Param("uid") String uid
			);
	
	/**
	 * 用艺人完成演艺后的发布人给出的各项打分值来修改艺人的各项评价的综合值
	 * @param uid					艺人的id
	 * @param performanceid			演艺活动的id
	 * @return
	 */
	public Integer updateArtistofEvaluate(@Param("uid") String uid,
			@Param("performanceid") String performanceid);
	
}
