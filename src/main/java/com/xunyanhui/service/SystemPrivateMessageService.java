/**
 * 创建日期：2016-12-10下午2:03:38
 * 修改日期：
 * 作者：邢传军
 * 目的：系统内私信操作类
 */
package com.xunyanhui.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xunyanhui.model.SystemPrivateMessageList;


public interface SystemPrivateMessageService {
	
	/**
	 * 用户向指定用户发系统私信
	 * 
	 * @param 
	 * from  				发信人
	 * to    				收信人
	 * content				信内容
	 * @return 信息内容写入是否成功
	 */
	public int insertSingleMessage(String from,String to,String content);
	
	/**
	 * 读取指定用户接收到各个用户的最后一次私聊信息
	 * @param uid			指定用户的uid
	 * @param page			读取的页号
	 * @param size			读取的页的大小
	 * @return
	 */
	public List<SystemPrivateMessageList> getSingleMessageList(String uid,int page,int size);

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
	 * 获得指定用户的当前收到消息总数
	 * @param uid			指定用户的uid
	 * @return
	 */
	public int getSingleMessageCount(String uid);
	/**
	 * 计算两者之间发送的信息的总条数
	 * @param uid			读取用户id
	 * @param sid			发送用户id
	 * @return
	 */
	public Integer conutSingleMessageListBySender(String uid,String sid);
}
