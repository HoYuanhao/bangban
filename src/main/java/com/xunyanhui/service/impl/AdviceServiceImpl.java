/**
 * 创建日期：2017-1-11下午4:02:46
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.service.impl;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.xunyanhui.dao.AdviceDao;
import com.xunyanhui.model.Advice;

import com.xunyanhui.service.AdviceService;

@Service
public class AdviceServiceImpl implements AdviceService {

	final Logger logger = Logger.getLogger("AccountService");
	@Autowired
	private AdviceDao adviceDao;


	
	@Override
	public int newAdvice(Advice advice) {
		// TODO Auto-generated method stub
		return adviceDao.newAdvice(advice);
	}

	
	

}
