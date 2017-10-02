/**
 * 创建日期：2017-1-4下午1:02:27
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xunyanhui.bean.ArtistPerforScoreDetail;
import com.xunyanhui.dao.EvaluateDao;
import com.xunyanhui.dao.MyPerformanceDao;
import com.xunyanhui.model.ArtistEvaluateType;
import com.xunyanhui.model.ArtistPerforEvaluate;
import com.xunyanhui.service.EvaluateService;
import com.xunyanhui.utils.UUidUtil;

@Service
public class EvaluateServiceImpl implements EvaluateService {

	final Logger logger = Logger.getLogger("MyInfoDao");
	@Autowired
	private EvaluateDao evaluateDao;
	@Autowired
	private MyPerformanceDao myPerformanceDao;
	
	
	
	@Override
	public Integer isGoodLevel(String objectid, String releaseid, int evaluatetype) {
		// TODO Auto-generated method stub
		return evaluateDao.isGoodLevel(objectid, releaseid, evaluatetype);
	}
	
	
	@Override
	public Integer inGoodLevel(String objectid, String releaseid,
			int evaluatetype,int state) {
		// TODO Auto-generated method stub
		return evaluateDao.inGoodLevel(objectid, releaseid, evaluatetype,state);
	}
	
	
	@Override
	public Integer newGoodLevel( String id,String objectid, String releaseid,
			int evaluatetype, int state,String acceptid) {
		// TODO Auto-generated method stub
		return evaluateDao.newGoodLevel(id,objectid, releaseid, evaluatetype, state,acceptid);
	}

	
	@Override
	public Integer isScore(String uid, String performanceid) {
		// TODO Auto-generated method stub
		return evaluateDao.isScore(uid, performanceid);
	}
	
	
	@Override
	public List<ArtistEvaluateType> getScoreList() {
		// TODO Auto-generated method stub
		return evaluateDao.getScoreList();
	}
	
	
	
	@Override
	public List<ArtistPerforEvaluate> getScoreArtistOfPerfor(String artistid,
			String performanceid) {
		// TODO Auto-generated method stub
		return evaluateDao.getScoreArtistOfPerfor(artistid, performanceid);
	}

	
	@Override
	public String getCommentArtistOfPerfor(String uid, String artistid,
			String performanceid) {
		// TODO Auto-generated method stub
		return evaluateDao.getCommentArtistOfPerfor(uid, artistid, performanceid);
	}
	
	
	
	@Override
	public Integer getGoodLevel(String objectid, String releaseid,
			int evaluatetype) {
		return evaluateDao.getGoodLevel(objectid, releaseid, evaluatetype);
	}
	@Override
	public Integer getGoodLevelOfPerfor(String objectid, String releaseid,
			int evaluatetype, String acceptid) {
		return evaluateDao.getGoodLevelOfPerfor(objectid, releaseid, evaluatetype,acceptid);
	}
	
	
	
	@Override
	public Integer newCommentArtist(String id, String objectid,
			String releaseid, int evaluatetype, String description,String artistid) {
		// TODO Auto-generated method stub
		return evaluateDao.newCommentArtist(id, objectid, releaseid, evaluatetype, description,artistid);
	}
	
	
	@Override
	public Integer addArtistPerforEvaluate(List<ArtistPerforEvaluate> scoreList) {
		// TODO Auto-generated method stub
		return evaluateDao.addArtistPerforEvaluate(scoreList);
	}
	
	
	@Override
	public String getOpusAuthor(String oid) {
		// TODO Auto-generated method stub
		return evaluateDao.getOpusAuthor(oid);
	}
	
	
	@Override
	public Integer updatePriseOfOpus(int value, String oid, String uid) {
		// TODO Auto-generated method stub
		return evaluateDao.updatePriseOfOpus(value, oid, uid);
	}
	
	
	@Override
	public Integer updatePriseOfArtist(String uid) {
		// TODO Auto-generated method stub
		return evaluateDao.updatePriseOfArtist(uid);
	}


	/*
	 * 完成演艺活动后发布人对艺人的评价
	 * releaseid			发布人id
	 * artistid				被评价人/艺人id
	 * objectid				被评价的关联的演艺活动id
	 * perfBean1			评价内容详情
	 * 
	 */
	@Override
	@Transactional
	public Integer setEvaluate(String releaseid, String artistid,
			String objectid, ArtistPerforScoreDetail perfBean1) {
		Integer ret = 0;
		int evaluatetype = 3;
		String comm = perfBean1.getEvaluation();
		//	写入演艺的评论(本质上是对艺人的评价)
		int comm_ret = evaluateDao.newCommentArtist(
				UUidUtil.getUUid(), objectid, releaseid, evaluatetype,
				comm, artistid);
		//	给好评
		int good_ret = evaluateDao.newGoodLevel(UUidUtil.getUUid(),
				objectid, releaseid, evaluatetype,
				perfBean1.getGoodLevel(),artistid);
		//修改用户的完成数
		int com_ret = evaluateDao.updateCompleteOfArtist(artistid);
		//	修改用户的好评数和好评率
		if(perfBean1.getGoodLevel()==1){
			evaluateDao.updatePriseOfArtist(artistid);
		}
		evaluateDao.updatePriseRateOfArtist(artistid);
		//	写入打分值
		int score_ret = evaluateDao
				.addArtistPerforEvaluate(perfBean1.getScoreList());
		//修改艺人的各项评价的分值
		int update_artist = evaluateDao.updateArtistofEvaluate(artistid,objectid);
		
		//	修改演艺活动报名状态
		int is_State = myPerformanceDao.updateEntryState(
				artistid, objectid, 7);
		//	修改艺人的评价项的值
		if (comm_ret >= 1 && good_ret >= 1 && score_ret >= 1&&is_State>=1&&update_artist>=1&&com_ret==1){
			ret = 1;
		}
		return ret;
	}

}
