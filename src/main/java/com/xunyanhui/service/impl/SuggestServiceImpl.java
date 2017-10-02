/**
 * 创建日期：2017-3-2下午4:02:46
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.service.impl;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.xunyanhui.bean.Suggest;

import com.xunyanhui.dao.SuggestDao;

import com.xunyanhui.service.SuggestService;

@Service
public class SuggestServiceImpl implements SuggestService {

	final Logger logger = Logger.getLogger("SuggestService");
	@Autowired
	private SuggestDao suggestDao;
	


	@Override
	public int newSuggest(Suggest suggest) {
		// TODO Auto-generated method stub
		return suggestDao.newSuggest(suggest);
	}
	
	

}
