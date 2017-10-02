/**
 * 创建日期：2016-12-17上午10:47:03
 * 修改日期：
 * 作者：邢传军
 * 目的：认证文件处理service
 */
package com.xunyanhui.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.xunyanhui.dao.AuthFileDao;
import com.xunyanhui.service.AuthFileService;

@Service
public class AuthFileServiceImpl implements AuthFileService {
	final Logger logger = Logger.getLogger("AuthFileDao");
	@Autowired
	private AuthFileDao authFileDao;

	/* (non-Javadoc)
	 * @see com.xunyanhui.service.AuthFileService#insertAuthFile(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	
	@Override
	public int insertAuthFile(String id, String type, String authname,
			String idnum, String filename, String path, String uid) {
		int ret = 0;
		ret = authFileDao.insertAuthFile(id, type, authname, idnum, filename, path, uid);
		return ret;
	}

}
