/**
 * 创建日期：2016-12-16下午3:11:22
 * 修改日期：
 * 作者：邢传军
 * 目的：系统消息操作DAO
 */
package com.xunyanhui.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xunyanhui.model.SystemMessage;
import com.xunyanhui.model.SystemMessageList;

public interface SystemMessageDao {
	/**
	 * 获取指定用户的保存的全部系统消息，分页模式
	 * @param uid			指定用户id
	 * @param page			读取的页号
	 * @param size			读取的页内消息数
	 * @return
	 */
	public List<SystemMessageList> getSystemMessageList(
			@Param("uid") String uid,
			@Param("date") Long date,
			@Param("page") int page,
			@Param("size") int size
			
			);
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
	public Integer updateSysMsgState(
			@Param("id") String id
			);

}
