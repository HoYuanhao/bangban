/**
 * 创建日期：2016-12-23下午10:16:28
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunyanhui.bean.ArtistAnnouncementV;
import com.xunyanhui.bean.EvaluationOpus;
import com.xunyanhui.bean.MyArtistOpus;
import com.xunyanhui.bean.MyArtistOpusL;
import com.xunyanhui.bean.MyPerformance;
import com.xunyanhui.bean.MyPerformanceDetail;

import com.xunyanhui.bean.SignupArtist;
import com.xunyanhui.bean.SignupArtistOfPerf;
import com.xunyanhui.dao.AccountDao;

import com.xunyanhui.dao.MyPerformanceDao;
import com.xunyanhui.model.ArtPerformanceEntry;
import com.xunyanhui.model.ArtistAnnouncement;
import com.xunyanhui.model.EntryRecordN;
import com.xunyanhui.model.TransactionRecord;
import com.xunyanhui.service.MyPerformanceService;

@Service
public class MyPerformanceServiceImpl implements MyPerformanceService {

	final Logger logger = Logger.getLogger("MyPerformanceDao");
	@Autowired
	private MyPerformanceDao myPerformanceDao;
	@Autowired
	private AccountDao accountDao;
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#getArtPerformanceEntryList(java.lang.String)
	 */
	@Override
	public List<ArtPerformanceEntry> getArtPerformanceEntryList(String uid) {
		// TODO Auto-generated method stub
		List<ArtPerformanceEntry> list = myPerformanceDao.getArtPerformanceEntryList(uid);
		return list;
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#getSelfOpusList(java.lang.String)
	 */
	@Override
	public List<MyArtistOpus> getSelfOpusList(String uid) {
		// TODO Auto-generated method stub
		List<MyArtistOpus> list = myPerformanceDao.getSelfOpusList(uid);
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#getSelfOpusById(java.lang.String)
	 */
	@Override
	public MyArtistOpusL getSelfOpusById(String objectid) {
		// TODO Auto-generated method stub
		return myPerformanceDao.getSelfOpusById(objectid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#getPostOfSelfOpus(java.lang.String)
	 */
	@Override
	public List<EvaluationOpus> getPostOfSelfOpus(String objectid) {
		// TODO Auto-generated method stub
		return myPerformanceDao.getPostOfSelfOpus(objectid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#getSendList(java.lang.String)
	 */
	@Override
	public List<MyPerformance> getSendList(String uid) {
		// TODO Auto-generated method stub
		return myPerformanceDao.getSendList(uid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#getEnableList(java.lang.String)
	 */
	@Override
	public List<MyPerformance> getEnableList(String uid) {
		// TODO Auto-generated method stub
		return myPerformanceDao.getEnableList(uid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#getAnnounceOfArtist(java.lang.String)
	 */
	@Override
	public List<ArtistAnnouncementV> getAnnounceOfArtist(String uid) {
		// TODO Auto-generated method stub
		return myPerformanceDao.getAnnounceOfArtist(uid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#getAttention(java.lang.String, java.lang.String)
	 */
	@Override
	public int getAttention(String uid, String oid) {
		// TODO Auto-generated method stub
		return myPerformanceDao.getAttention(uid, oid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#newAttention(java.lang.String, java.lang.String)
	 */
	@Override
	public int newAttention(String uid, String oid,String uaid) {
		// TODO Auto-generated method stub
		return myPerformanceDao.newAttention(uid, oid,uaid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#updateAttention(java.lang.String, java.lang.String, int)
	 */
	@Override
	public int updateAttention(String uid, String uaoid, int uastate) {
		// TODO Auto-generated method stub
		return myPerformanceDao.updateAttention(uid, uaoid, uastate);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#newAnnounce(com.xunyanhui.model.ArtistAnnouncement)
	 */
	@Override
	public int newAnnounce(ArtistAnnouncement artistAnnouncement) {
		// TODO Auto-generated method stub
		return myPerformanceDao.newAnnounce(artistAnnouncement);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#cancelAnnounce(java.lang.String, java.lang.String)
	 */
	@Override
	public int cancelAnnounce(String uid, String aid) {
		// TODO Auto-generated method stub
		return myPerformanceDao.cancelAnnounce(uid, aid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#isArtist(java.lang.String)
	 */
	@Override
	public int isArtist(String uid) {
		// TODO Auto-generated method stub
		return myPerformanceDao.isArtist(uid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#signupArtistOfPerf(java.lang.String)
	 */
	@Override
	public List<SignupArtist> signupArtistOfPerf(String performanceid) {
		// TODO Auto-generated method stub
		return myPerformanceDao.signupArtistOfPerf(performanceid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#signupArtistOfPerfByUser(java.lang.String, java.lang.String)
	 */
	@Override
	public List<SignupArtistOfPerf> signupArtistOfPerfByUser(
			String performanceid, String uid) {
		// TODO Auto-generated method stub
		return myPerformanceDao.signupArtistOfPerfByUser(performanceid, uid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#getPerformanceDetailsOfUser(java.lang.String, java.lang.String)
	 */
	@Override
	public MyPerformanceDetail getPerformanceDetailsOfUser(String uid,
			String performanceid) {
		// TODO Auto-generated method stub
		return myPerformanceDao.getPerformanceDetailsOfUser(uid, performanceid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#checkPerformanceOfUser(java.lang.String, java.lang.String)
	 */
	@Override
	public int checkPerformanceOfUser(String uid, String performanceid) {
		// TODO Auto-generated method stub
		return myPerformanceDao.checkPerformanceOfUser(uid, performanceid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#updateEntryState(java.lang.String, java.lang.String, int)
	 */
	@Override
	public int updateEntryState(String artistid, String performanceid, int state) {
		// TODO Auto-generated method stub
		return myPerformanceDao.updateEntryState(artistid, performanceid, state);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#getEntryRecord(java.lang.String, java.lang.String)
	 */
	@Override
	public EntryRecordN getEntryRecord(String artistid, String performanceid) {
		// TODO Auto-generated method stub
		return myPerformanceDao.getEntryRecord(artistid, performanceid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#updateSignUpState(java.lang.String, java.lang.String, int)
	 */
	@Override
	public int updateSignUpState(String uid, String performanceId,
			int signup_state) {
		// TODO Auto-generated method stub
		return myPerformanceDao.updateSignUpState(uid, performanceId, signup_state);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyPerformanceService#updatePrice(java.lang.String, java.lang.String, int, java.lang.String)
	 */
	@Override
	public int updatePrice(String artistid, String performanceId, int price,
			String description) {
		// TODO Auto-generated method stub
		return myPerformanceDao.updatePrice(artistid, performanceId, price, description);
	}
	/*
	 * 实现用户的雇佣TA的功能，只要完成一下工作：
	 * 1、创建交易记录状态为：交易发起，支出人为releaseid，收款人为artistid，关联的演艺活动id为performanceId
	 * 2、修改发起人的账户余额
	 * 3、修改演艺活动报名状态
	 */
	
	@Override
	@Transactional
	public int inOrder(String artistid, String performanceid, int price,
			String releaseid) {
		int ret = 0;
		//创建交易记录
		TransactionRecord tr = new TransactionRecord();
		Date date = new Date();
		long timestamp = date.getTime();
		String trid = releaseid.length()>5?releaseid.substring(0,5) + timestamp:releaseid + timestamp;
		tr.setTrid(trid);
		tr.setPaymentId(releaseid);
		tr.setReceiveId(artistid);
		tr.setDescription("演艺活动报酬");
		tr.setChannel("内部支付");
		tr.setMoney(price);
		tr.setPerformanceId(performanceid);
		tr.setType(3);
		tr.setState(1);
		tr.setTimestamp(Long.valueOf(timestamp).toString());
		int create_num = accountDao.newTR(tr);
		//修改支付方的账户余额
		int update_pay = accountDao.updateBalance(releaseid, -price);
		//修改报名状态,为成交 
		int update_entry = myPerformanceDao.updateEntryState(artistid, performanceid, 1);
		if(create_num==1&&update_pay==1&&update_entry==1)
			ret = 1;
		return ret;
	}
	/*
	 * 实现用户的“结算”的功能，只要完成以下工作：
	 * 1、修改交易状态为：2：支付方审核，支出人为releaseid，收款人为artistid，关联的演艺活动id为performanceid
	 * 2、修改报名活动的状态为：2表示完成待支付
	 */
	@Override
	@Transactional
	public int inCount(String artistid, String performanceid, String releaseid) {
		int ret = 0;
		//修改交易状态
		int update_pay = accountDao.updateTRStateByPerformance(releaseid, artistid, performanceid,2);
		//修改报名状态,为成交 
		int update_entry = myPerformanceDao.updateEntryState(artistid, performanceid, 2);
		if(update_pay==1&&update_entry==1)
			ret = 1;
		return ret;
	}
	/*
	 * 实现用户的“支付功能”的功能，只要完成以下工作：
	 * 1、修改交易状态为：8：状态完毕，支出人为releaseid，收款人为artistid，关联的演艺活动id为performanceid
	 * 2、修改报名活动的状态为：3表示支付完毕
	 * 3、修改接收方的账户余额为：加上报名价格
	 */
	@Override
	public int inPay(String artistid, String performanceid, int price,
			String releaseid) {
		int ret = 0;
		//修改交易状态
		int update_pay = accountDao.updateTRStateByPerformance(releaseid, artistid, performanceid,8);
		//修改报名状态,3表示支付完毕
		int update_entry = myPerformanceDao.updateEntryState(artistid, performanceid, 3);
		//修改支付方的账户余额
		int update_receive = accountDao.updateBalance(artistid, price);
		if(update_receive==1&&update_pay==1&&update_entry==1)
			ret = 1;
		return ret;
	}

}
