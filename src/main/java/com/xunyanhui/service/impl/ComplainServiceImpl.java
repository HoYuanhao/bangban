/**
 * 创建日期：2017-3-2下午4:02:46
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.service.impl;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.xunyanhui.dao.ComplainDao;
import com.xunyanhui.dao.MyPerformanceDao;

import com.xunyanhui.model.Complain;

import com.xunyanhui.service.ComplainService;

@Service
public class ComplainServiceImpl implements ComplainService {

	final Logger logger = Logger.getLogger("ComplainService");
	@Autowired
	private ComplainDao complainDao;
	@Autowired
	private MyPerformanceDao myPerformanceDao;
	
	@Override
	@Transactional
	public int newComplain(Complain complain) {
		int ret=0;
		int ret_newcomplain,ret_entry_state;
		ret_newcomplain = complainDao.newComplain(complain);
		ret_entry_state = myPerformanceDao.updateEntryState(complain.getArtistid(), complain.getPerformanceid(), 5);
		if(ret_newcomplain==1&&ret_entry_state==1)
			ret = 1;
		return ret;
	}

	/* (non-Javadoc)
	 * @see com.xunyanhui.service.ComplainService#getComplain(java.lang.String, java.lang.String)
	 */
	@Override
	public Complain getComplain(String artistid, String performanceid) {
		// TODO Auto-generated method stub
		return complainDao.getComplain(artistid, performanceid);
	}
	
	

}
