package com.xunyanhui.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyanhui.bean.UserInfoForEasemob;

import com.xunyanhui.dao.UserDao;

import com.xunyanhui.model.User;
import com.xunyanhui.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	final Logger logger = Logger.getLogger("UserService");

	@Autowired
	private UserDao userDao;


	@Override
	public User getUserById(String id) {
		logger.info("getArtistList方法开始");
		User user = null;
		try {
			logger.info("userDao.getUserById()\t从数据库中取出数据");
			user = userDao.getUserById(id);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("userDao.getUserById(id)出现错误" + e.getMessage());
			return null;
		}

		logger.info("getArtistList结束");
		return user;
	}

	@Override
	public int login(String loginName, String passwd) {
		logger.info("getArtistList方法开始");
		int rtn = -1;
		try {
			User user = userDao.getUserByUserNameAndPasswd(loginName, passwd);
			if (user != null) {
				rtn = LOGIN_SUCCESS;
				Subject subject = SecurityUtils.getSubject();
				Session session = subject.getSession();
				session.setAttribute(LOGIN_USER, user);

			} else {
				int result = userDao.hasUserByUserName(loginName);
				if (result == 1) {
					rtn = LOGIN_PASSWORD_FAIL;
				} else {
					rtn = LOGIN_USER_NAME_FAIL;
				}
			}
		} catch (Exception e) {
			logger.error("userDao.getUserByUserNameAndPasswd(userName, passwd)\t"
					+ e.getMessage());
			return LOGIN_ERRO;
		}

		logger.info("getArtistList结束");
		return rtn;
	}

	@Override
	public boolean regist(User user) {
		try {

			userDao.registUser(user);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	@Override
	public boolean hasPhoneNum(String phoneNum) {
		// TODO Auto-generated method stub
		
		try {
			
			int ret = userDao.hasMobile(phoneNum);
			
			if ( ret > 0) {
				
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("phoneNum22222"+phoneNum);
		return false;
	}

	@Override
	public boolean hasUserName(String userName) {
		// TODO Auto-generated method stub
		if (userDao.hasUserByUserName(userName) > 0) {
			return true;
		}
		return false;
	}
	public String getCid(String id) {
		// TODO Auto-generated method stub
		return userDao.getCid(id);
	}
	/**
	 * 用于将当前所用用户添加到环信的用户系统中
	 */
	public List<UserInfoForEasemob> selectAllUser(){
		return userDao.selectAllUser();
	}

	/* (non-Javadoc)
	 * @see com.xunyanhui.service.UserService#updatePasswdByMobile(java.lang.String, java.lang.String)
	 */
	@Override
	public int updatePasswdByMobile(String mobile, String newPasswd) {
		// TODO Auto-generated method stub
		return userDao.updatePasswdByMobile(mobile, newPasswd);
		
	}

	/* (non-Javadoc)
	 * @see com.xunyanhui.service.UserService#updatePasswd(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public int updatePasswd(String id, String oldPasswd, String newPasswd) {
		// TODO Auto-generated method stub
		return userDao.updatePasswd(id, oldPasswd, newPasswd);
	}





}
