/**
 * 创建日期：2016-12-16下午3:25:53
 * 修改日期：
 * 作者：邢传军
 * 目的：系统消息处理
 */
package com.xunyanhui.service.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyanhui.dao.SystemMessageDao;
import com.xunyanhui.model.SystemMessage;
import com.xunyanhui.model.SystemMessageList;
import com.xunyanhui.service.SystemMessageService;

@Service
public class SystemMessageServiceImpl implements SystemMessageService {
	final Logger logger = Logger.getLogger("SystemMessageDao");
	@Autowired
	private SystemMessageDao systemMessageDao;
	
	
	@Override
	public List<SystemMessageList> getSystemMessageList(String uid,Long date,int page,int size) {
		// TODO Auto-generated method stub
		List<SystemMessageList> list = systemMessageDao.getSystemMessageList(uid,date,(page-1)*size, size);
		return list;
	}


	/* (non-Javadoc)
	 * @see com.xunyanhui.service.SystemMessageService#newSystemMessage(com.xunyanhui.model.SystemMessage)
	 */
	@Override
	public Integer newSystemMessage(SystemMessage systemMessage) {
		// TODO Auto-generated method stub
		return systemMessageDao.newSystemMessage(systemMessage);
	}


	/* (non-Javadoc)
	 * @see com.xunyanhui.service.SystemMessageService#updateSysMsgState(java.lang.String)
	 */
	@Override
	public Integer updateSysMsgState(String id) {
		// TODO Auto-generated method stub
		return systemMessageDao.updateSysMsgState(id);
	}

}
