/**
 * 创建日期：2016-12-22下午5:35:58
 * 修改日期：
 * 作者：邢传军
 * 目的：用户定义演艺活动情况
 */
package com.xunyanhui.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyanhui.bean.Performancetype;
import com.xunyanhui.bean.UserSubscribeV;
import com.xunyanhui.bean.UserSubscribeVS;

import com.xunyanhui.dao.UserSubscribeDao;
import com.xunyanhui.service.UserSubscribeService;
@Service
public class UserSubscribeServiceImpl implements UserSubscribeService {
	final Logger logger = Logger.getLogger("UserSubscribeDao");
	@Autowired
	private UserSubscribeDao userSubscribeDao;
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.UserSubscribeService#selectUserSubscribeByUid(java.lang.String)
	 */
	@Override
	public List<UserSubscribeV> selectUserSubscribeByUid(String uid) {
		// TODO Auto-generated method stub
		
		return userSubscribeDao.selectUserSubscribeByUid(uid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.UserSubscribeService#updateUserSubscribeByUid(java.lang.String, int, int)
	 */
	@Override
	public int updateUserSubscribeByUid(String uid, int pid, int substate) {
		// TODO Auto-generated method stub
		return userSubscribeDao.updateUserSubscribeByUid(uid, pid, substate);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.UserSubscribeService#selectSubscribeByPid(int)
	 */
	@Override
	public List<UserSubscribeVS> selectSubscribeByPid(int pid) {
		// TODO Auto-generated method stub
		return userSubscribeDao.selectSubscribeByPid(pid);
	}
	@Override
	public List<Performancetype> selectPerformancetype() {
		
		return userSubscribeDao.selectPerformancetype();
	}
	@Override
	public boolean addSubscribeBypid(List<UserSubscribeV> usersublist) {

		userSubscribeDao.addSubscribeBypid(usersublist);
		return true;
		
	}

	
}
