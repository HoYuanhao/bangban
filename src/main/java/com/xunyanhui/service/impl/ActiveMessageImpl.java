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

import com.xunyanhui.dao.ActiveMessageDao;

import com.xunyanhui.model.ActiveMessageList;
import com.xunyanhui.service.ActiveMessageService;
import com.xunyanhui.utils.UUidUtil;


@Service
public class ActiveMessageImpl implements ActiveMessageService {
	final Logger logger = Logger.getLogger("ActiveMessageDao");
	@Autowired
	private ActiveMessageDao activeMessageDao;
	@Override
	public int insertActiveMessage(String releaseid,String img,String details,String topic,String scope,String path){
		logger.info("insertActiveMessage方法开始");
		
		int ret = 0;
		String id = UUidUtil.getUUid();
		ret = activeMessageDao.insertActiveMessage(id,releaseid,img,details,topic,scope,path);
		return ret;
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.SystemPrivateMessage#getList(java.lang.String, int, int)
	 */
	@Override
	public List<ActiveMessageList> getActiveMessageList(int page, int size) {
		// TODO Auto-generated method stub
		List<ActiveMessageList> list = activeMessageDao.getActiveMessageList((page-1)*size, size);
		return list;
	}
	
	
	public int getActiveMessageCount(){
		int count = activeMessageDao.getActiveMessageCount();
		return count;
		
	}
}

