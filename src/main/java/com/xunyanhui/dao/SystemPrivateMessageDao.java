/**
 * 创建日期：2016-12-10下午2:10:59
 * 修改日期：
 * 作者：邢传军
 * 目的：系统私信的dao
 */
package com.xunyanhui.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.xunyanhui.model.SystemPrivateMessage;
import com.xunyanhui.model.SystemPrivateMessageList;


/**
 *
 */
/**
 *
 */
public interface SystemPrivateMessageDao {

	
	/**
	 * @param id			插入的私信id
	 * @param releaseid		发布人id
	 * @param acceptid		接收人id
	 * @param details		发布的消息内容
	 * @return 插入记录数
	 */
	public int insertSingleMessage(
			@Param("id") String id,
			@Param("releaseid") String releaseid,
			@Param("acceptid") String acceptid,
			@Param("details") String details);
	
	/**
	 * @param uid			读取用户id
	 * @param page			读取的页号
	 * @param size			读取的页内消息数
	 * @return
	 */
	public List<SystemPrivateMessageList> getSingleMessageList(
			@Param("uid") String uid,
			@Param("page") int page,
			@Param("size") int size
			);
	
	/**
	 * 分页读取sid用户发送给指定用户的消息列表，按照
	 * @param uid			读取用户id
	 * @param sid			发送用户id
	 * @param page			读取的页号
	 * @param size			读取的页内消息数
	 * @return
	 */
	public List<SystemPrivateMessageList> getSingleMessageListBySender(
			@Param("uid") String uid,
			@Param("sid") String sid,
			@Param("page") int page,
			@Param("size") int size
			);
	/**
	 * 计算两者之间发送的信息的总条数
	 * @param uid			读取用户id
	 * @param sid			发送用户id
	 * @return
	 */
	public Integer conutSingleMessageListBySender(
			@Param("uid") String uid,
			@Param("sid") String sid
			);
	
	/**
	 * @param uid			读取的用户id
	 * @return
	 */
	public int getSingleMessageCount(
			@Param("uid") String uid
	);



}
