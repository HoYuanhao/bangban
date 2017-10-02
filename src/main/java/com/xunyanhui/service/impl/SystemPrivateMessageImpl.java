/**
 * 创建日期：2016-12-10下午2:10:59
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.service.impl;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyanhui.dao.SystemPrivateMessageDao;

import com.xunyanhui.model.SystemPrivateMessageList;
import com.xunyanhui.service.SystemPrivateMessageService;
import com.xunyanhui.utils.UUidUtil;


@Service
public class SystemPrivateMessageImpl implements SystemPrivateMessageService {
	final Logger logger = Logger.getLogger("SystemPrivateMessageDao");
	@Autowired
	private SystemPrivateMessageDao systemPrivateMessageDao;
	@Override
	public int insertSingleMessage(String from,String to,String content){
		logger.info("getArtistList方法开始");
		logger.info("从数据库中获取List<User>对象");
		int ret = 0;
		String id = UUidUtil.getUUid();
		ret = systemPrivateMessageDao.insertSingleMessage(id,from, to, content);
		return ret;
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.SystemPrivateMessage#getList(java.lang.String, int, int)
	 */
	@Override
	public List<SystemPrivateMessageList> getSingleMessageList(String uid, int page, int size) {
		// TODO Auto-generated method stub
		List<SystemPrivateMessageList> list = systemPrivateMessageDao.getSingleMessageList(uid, (page-1)*size, size);
		return list;
	}
	
	/**
	 * 分页读取sid用户发送给指定用户的消息列表，按照
	 * @param uid			读取用户id
	 * @param sid			发送用户id
	 * @param page			读取的页号
	 * @param size			读取的页内消息数
	 * @return
	 */
	public List<SystemPrivateMessageList> getSingleMessageListBySender(String uid,String sid,int page,int size){
		List<SystemPrivateMessageList> list = systemPrivateMessageDao.getSingleMessageListBySender(uid,sid, page, size);
		return list;
	}
	
	
	
	public int getSingleMessageCount(String uid){
		int count = systemPrivateMessageDao.getSingleMessageCount(uid);
		return count;
		
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.SystemPrivateMessageService#conutSingleMessageListBySender(java.lang.String, java.lang.String)
	 */
	@Override
	public Integer conutSingleMessageListBySender(String uid, String sid) {
		// TODO Auto-generated method stub
		return systemPrivateMessageDao.conutSingleMessageListBySender(uid, sid);
	}
}

