/**
 * 创建日期：2017-1-3下午12:53:49
 * 修改日期：
 * 作者：邢传军
 * 目的：用于艺人个人信息的完善和处理
 */
package com.xunyanhui.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xunyanhui.bean.ArtistAttenListV;
import com.xunyanhui.bean.ArtistSelfInfo;
import com.xunyanhui.bean.ArtistSelfMoreInfo;
import com.xunyanhui.bean.AuthState;
import com.xunyanhui.bean.UserInfoSimple;
import com.xunyanhui.model.ArtPerformanceEntry;

public interface MyInfoDao {
	
	/**
	 * 用于更新和维护艺人头像是来保存艺人头像的文件类型
	 * @param artistid			艺人id，该id与用户id相同
	 * @param pic				艺人头像的类型
	 * @return
	 */
	public int updatePic(
			@Param("pic") String pic,
			@Param("artistid") String artistid
			);
	/**
	 * 为用户创建艺人账号
	 * @param uid			用户id
	 * @return
	 */
	public int newArtist (
			@Param("uid") String uid,
			@Param("uname") String uname
	);
	/**
	 * 读取艺人的详细信息
	 * @param uid			用户id
	 * @return
	 */
	public ArtistSelfInfo getArtistById (
			@Param("artistid") String artistid
	);
	/**
	 * 读取用户的简要信息
	 * @param uid			用户id
	 * @return
	 */
	public UserInfoSimple getSimpleUserInfo (
			@Param("uid") String uid
	);
	/**
	 * 用于更新和维护艺人艺名
	 * @param artistid				艺人id，该id与用户id相同
	 * @param stageName				艺人的新艺名
	 * @return
	 */
	public int updateStageName(
			@Param("stageName") String stageName,
			@Param("artistid") String artistid
			);
	/**
	 * 用于更新和维护艺人演艺经历
	 * @param artistid						艺人id，该id与用户id相同
	 * @param performancelist				艺人的演艺经历
	 * @return
	 */
	public int updatePerfor(
			@Param("performancelist") String performancelist,
			@Param("artistid") String artistid
			);
	/**
	 * 用于更新和维护艺人获奖经历
	 * @param artistid						艺人id，该id与用户id相同
	 * @param biographylist					艺人的获奖经历
	 * @return
	 */
	public int updateBiographyList(
			@Param("biographylist") String biographylist,
			@Param("artistid") String artistid
			);
	/**
	 * 用于更新和维护艺人最高奖项
	 * @param artistid						艺人id，该id与用户id相同
	 * @param biographyhighest				艺人的最高奖项
	 * @return
	 */
	public int updateBiographyHighest(
			@Param("biographyhighest") String biographyhighest,
			@Param("artistid") String artistid
			);
	/**
	 * 用于更新和维护艺人自我介绍
	 * @param artistid						艺人id，该id与用户id相同
	 * @param selfintroduction				艺人的自我介绍
	 * @return
	 */
	public int updateSelfIntro(
			@Param("selfintroduction") String selfintroduction,
			@Param("artistid") String artistid
			);
	/**
	 * 用于更新和维护艺人特长
	 * @param artistid						艺人id，该id与用户id相同
	 * @param specialty						艺人的擅长
	 * @return
	 */
	public int updateSpecialty(
			@Param("specialty") String specialty,
			@Param("artistid") String artistid
			);
	/**
	 * 用于更新和维护艺人类型
	 * @param artistid						艺人id，该id与用户id相同
	 * @param type							艺人的类型
	 * @return
	 */
	public int updateType(
			@Param("type") String type,
			@Param("artistid") String artistid
			);
	/**
	 * 修改艺人的详细信息
	 * @param artistSelfInfo					艺人的更多信息
	 * @return
	 */
	public int updateMore(ArtistSelfMoreInfo artistSelfInfo);
	/**
	 * 修改艺人出场费标准
	 * @param minsalary						艺人出场费的最低标准
	 * @param type							艺人的类型
	 * @return
	 */
	public int updateMinSalary(
			@Param("minsalary") int minsalary,
			@Param("artistid") String artistid
			);
	/**
	 * 修改用户的个推id
	 * @param cid						用户设备对应的个推id
	 * @param id						用户id
	 * @return
	 */
	public int updateCidAndDeviceToken(
			@Param("cid") String cid,
			@Param("id") String id,
			@Param("devicetoken") String devicetoken
			);
	/**
	 * 读取用户的实名认证数据
	 * @param uid						用户id
	 * @return
	 */
	public AuthState getAuthOfUser(
			@Param("uid") String uid
			);
	/**
	 * 读取用户的实名认证状态
	 * @param uid						用户id
	 * @return
	 */
	public int getAuthStateOfUser(
			@Param("uid") String uid
			);
	/**
	 * 读取用户关注的艺人列表
	 * @param uid						用户id
	 * @return
	 */
	public List<ArtistAttenListV> getArtistListOfAtten(
			@Param("uid") String uid
			);
	/**
	 * 修改用户的实名认证状态
	 * @param uid						用户id
	 * isauth: 是否实名认证，1表示认证，0表示未认证
	 * authstate: 实名认证状态0表示未认证，1表示已经认证，2表示待认证,3表示认证未通过
	 * auditresult:审核结果:通过，拒绝，待审核
	 * @return
	 */
	public int updateAuthState(
			@Param("uid") String uid,
			@Param("isauth") int isauth,
			@Param("auditresult") String auditresult,
			@Param("authstate") int authstate,
			@Param("auditid") String auditid
			);
	
	

}
