/**
 * 创建日期：2016-12-16下午3:20:41
 * 修改日期：
 * 作者：邢传军
 * 目的：用于系统消息处理
 */
package com.xunyanhui.service;


import java.util.List;

import com.xunyanhui.model.SystemMessage;

import com.xunyanhui.model.SystemMessageList;

public interface SystemMessageService {
	
	/**
	 * 获取指定用户接收到全部系统消息，分页模式
	 * @param uid			指定的用户id
	 * @param page			读取的页号
	 * @param size			读取的页内消息数
	 * @param date			读取的消息时使用时间戳
	 * @return
	 */
	public List<SystemMessageList> getSystemMessageList(String uid,Long date,int page,int size);
	/**
	 * 插入系统消息
	 * @param systemMessage			系统消息内容
	 * @return
	 */
	public Integer newSystemMessage(SystemMessage systemMessage);
	/**
	 * 修改系统消息的发送状态
	 * @param id			指定系统消息id
	 * @return
	 */
	public Integer updateSysMsgState(String id);
	

}
