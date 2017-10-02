/**
 * 创建日期：2016-12-12下午2:10:59
 * 修改日期：
 * 作者：邢传军
 * 目的：活动消息的dao
 */
package com.xunyanhui.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.xunyanhui.model.ActiveMessage;
import com.xunyanhui.model.ActiveMessageList;


public interface ActiveMessageDao {

	

	/**
	 * 相系统活动表中添加一个活动
	 * @param id				活动id，使用uuid生成
	 * @param releaseid			发布人id
	 * @param img				活动的封面图片
	 * @param details			活动的细节描述
	 * @param topic				活动的主题
	 * @param scope				活动的通知范围
	 * @param path				封面图片的存储路径
	 * @return
	 */
	public int insertActiveMessage(
			@Param("id") String id,
			@Param("releaseid") String releaseid,
			@Param("img") String img,
			@Param("details") String details,
			@Param("topic") String topic,

			@Param("scope") String scope,
			@Param("path") String path
			);
	
	/**
	 * 获取系统中保存的全部活动消息，分页模式
	 * @param page			读取的页号
	 * @param size			读取的页内消息数
	 * @return
	 */
	public List<ActiveMessageList> getActiveMessageList(
			
			@Param("page") int page,
			@Param("size") int size
			);
	/**
	 * 读取系统存储的活动消息数量
	 * @return
	 */
	public int getActiveMessageCount(
	);



}
