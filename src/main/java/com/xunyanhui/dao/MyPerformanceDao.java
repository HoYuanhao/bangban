/**
 * 创建日期：2016-12-23下午9:54:51
 * 修改日期：
 * 作者：邢传军
 * 与我的报名演艺和发布演艺的相关DAO操作
 */
package com.xunyanhui.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xunyanhui.bean.ArtistAnnouncementV;
import com.xunyanhui.bean.EvaluationOpus;
import com.xunyanhui.bean.MyArtistOpus;
import com.xunyanhui.bean.MyArtistOpusL;
import com.xunyanhui.bean.MyPerformance;
import com.xunyanhui.bean.MyPerformanceDetail;
import com.xunyanhui.bean.Performance;
import com.xunyanhui.bean.SignupArtist;
import com.xunyanhui.bean.SignupArtistOfPerf;
import com.xunyanhui.model.ArtPerformanceEntry;
import com.xunyanhui.model.ArtistAnnouncement;
import com.xunyanhui.model.EntryRecord;
import com.xunyanhui.model.EntryRecordN;

public interface MyPerformanceDao {
	
	/**
	 * 读取指定的用户参加的演艺活动，
	 * @param uid			用户id
	 * @return
	 */
	public List<ArtPerformanceEntry> getArtPerformanceEntryList(
			
			@Param("uid") String uid
			
			);
	/**
	 * 读取指定的用户发布的小样/作品列表
	 * @param uid			用户id
	 * @return
	 */
	public List<MyArtistOpus> getSelfOpusList(
			@Param("uid") String uid
			);
	/**
	 * 用户报名指定演艺
	 * @param entryRecord
	 * @return
	 */
	
	public int newEnrty(
			@Param("erid") String erid,
			@Param("artistId") String artistId,
			@Param("performanceId") String performanceId,
			@Param("price") BigDecimal price,
			@Param("description") String description
			
			);
	
	/**
	 * 获取指定的小样/作品的详细信息
	 * @param objectid
	 * @return
	 */
	public MyArtistOpusL getSelfOpusById(@Param("objectid") String objectid);
	
	/**
	 * 获取指定的小样/作品的评价列表
	 * @param objectid
	 * @return
	 */
	
	public List<EvaluationOpus> getPostOfSelfOpus(@Param("objectid") String objectid);
	/**
	 * 获取用户发布的演艺活动列表
	 * @param uid
	 * @return
	 */
	public List<MyPerformance> getSendList(@Param("uid") String uid);
	/**
	 * 获取用户发布的某一演艺活动详情
	 * @param 				uid
	 * @param 				performanceid
	 * @return
	 */
	public MyPerformanceDetail getPerformanceDetailsOfUser(@Param("uid") String uid,@Param("performanceid") String performanceid);
	/**
	 * 检查用户是否为某一演艺的发布人
	 * @param 				uid
	 * @param 				performanceid
	 */
	public int checkPerformanceOfUser(@Param("uid") String uid,@Param("performanceid") String performanceid);
	/**
	 * 更改指定报名的报名状态
	 * @param 				artistid报名艺人的id
	 * @param 				performanceid
	 * @param				state,报名的状态
	 */
	public int updateEntryState(@Param("artistid") String artistid,@Param("performanceid") String performanceid,@Param("state") int state);
	
	/**
	 * 获取指定的报名的详情
	 * @param 				artistid报名艺人的id
	 * @param 				performanceid
	 */
	public EntryRecordN getEntryRecord(@Param("artistid") String artistid,@Param("performanceid") String performanceid);
	/**
	 * 获取用户发布可报名的演艺活动列表
	 * @param uid
	 * @return
	 */
	public List<MyPerformance> getEnableList(@Param("uid") String uid);
	
	/**
	 * 获取某一艺人的通告列表
	 * @param uid
	 * @return
	 */
	public List<ArtistAnnouncementV> getAnnounceOfArtist(@Param("uid") String uid);
	/**
	 * 获取某一艺人发布的演艺的报名艺人列表
	 * @param uid					演艺发布人id
	 * @param performanceid			演艺id
	 * @return
	 */
	public List<SignupArtistOfPerf> signupArtistOfPerf(@Param("uid") String uid,@Param("performanceid") String performanceid);
	
	/**
	 * 取消指定艺人的某一通告
	 * @param uid			艺人id
	 * @param aid			通告id
	 * @return
	 */
	
	public int cancelAnnounce(@Param("uid") String uid,@Param("aid") String aid);
	/**
	 * 发布一条通告
	 * @param artistAnnouncement
	 * @return
	 */
	public int newAnnounce(ArtistAnnouncement artistAnnouncement);
	/**
	 * 判断某一用户是否是艺人的
	 * @param uid
	 * @return
	 */
	public int isArtist(@Param("uid") String uid);
	/**
	 * 获取指定用户对某一艺人的关注情况
	 * @param uid
	 * @param oid
	 * @return
	 */
	public int getAttention(@Param("uid") String uid,@Param("oid") String oid);
	/**
	 * 指定用户对某一艺人的首次关注
	 * @param uid
	 * @param oid
	 * @return
	 */
	public int newAttention(@Param("uid") String uid,@Param("oid") String oid,@Param("uaid") String uaid);
	/**
	 * 维护用户对某一艺人的关注状态
	 * @param uid
	 * @param oid
	 * @return
	 */
	public int updateAttention(@Param("uid") String uid,@Param("uaoid") String uaoid,@Param("uastate") int uastate);
	
	/**
	 * 获取某一演艺活动报名的艺人的id和艺名
	 * @param performanceid
	 * @return
	 */
	public List<SignupArtist> signupArtistOfPerf(@Param("performanceid") String performanceid);
	/**
	 * 获取指定艺人发布某一演艺活动报名的艺人的详细信息
	 * @param performanceid
	 * @return
	 */
	public List<SignupArtistOfPerf> signupArtistOfPerfByUser(@Param("performanceid") String performanceid,@Param("uid") String uid);
	/**
	 * 更改演艺活动报名状态 
	 * @param uid			艺人id
	 * @param performanceId			演艺活动状态id
	 * @param signup_state			演艺活动报名状态1可以报名，0不可以报名
	 * @return
	 */
	
	public int updateSignUpState(@Param("uid") String uid,@Param("performanceId") String performanceId,@Param("signup_state") int signup_state);
	/**
	 * 修改演艺报名的报价，前提是报名时未成交状态 
	 * @param artistid				艺人id
	 * @param performanceId			演艺活动状态id
	 * @param price					修改后的演艺活动报价
	 * @param description			修改后的演艺活动报价说明
	 * @return
	 */
	
	public int updatePrice(@Param("artistid") String artistid,
			@Param("performanceid") String performanceid,
			@Param("price") int price,
			@Param("description") String description
			);
	/**
	 * 重新报名
	 * @param artistid				艺人id
	 * @param performanceId			演艺活动状态id
	 * @param price					修改后的演艺活动报价
	 * @param description			修改后的演艺活动报价说明
	 * @return
	 */
	
	public int reSignUp(@Param("artistid") String artistid,
			@Param("performanceid") String performanceid,
			@Param("price") int price,
			@Param("description") String description
			);
	
}
