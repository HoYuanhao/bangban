/**
 * 创建日期：2017-1-3下午1:01:19
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyanhui.bean.ArtistAttenListV;
import com.xunyanhui.bean.ArtistSelfInfo;
import com.xunyanhui.bean.ArtistSelfMoreInfo;
import com.xunyanhui.bean.AuthState;
import com.xunyanhui.bean.UserInfoSimple;
import com.xunyanhui.dao.MyInfoDao;

import com.xunyanhui.service.MyInfoService;

@Service
public class MyInfoServiceImpl implements MyInfoService {
	final Logger logger = Logger.getLogger("MyInfoDao");
	@Autowired
	private MyInfoDao myInfoDao;
	/* 
	 * @see com.xunyanhui.service.MyInfoService#updatePic(java.lang.String, java.lang.String)
	 */
	@Override
	public int updatePic(String pic, String artistid) {
		// TODO Auto-generated method stub
		return myInfoDao.updatePic(pic, artistid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyInfoService#newArtist(java.lang.String)
	 */
	@Override
	public int newArtist(String uid,String uname) {
		// TODO Auto-generated method stub
		return myInfoDao.newArtist(uid,uname);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyInfoService#getArtistById(java.lang.String)
	 */
	@Override
	public ArtistSelfInfo getArtistById(String artistid) {
		// TODO Auto-generated method stub
		return myInfoDao.getArtistById(artistid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyInfoService#getSimpleUserInfo(java.lang.String)
	 */
	@Override
	public UserInfoSimple getSimpleUserInfo(String uid) {
		// TODO Auto-generated method stub
		return myInfoDao.getSimpleUserInfo(uid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyInfoService#updateStageName(java.lang.String, java.lang.String)
	 */
	@Override
	public int updateStageName(String stageName, String artistid) {
		// TODO Auto-generated method stub
		return myInfoDao.updateStageName(stageName, artistid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyInfoService#updatePerfor(java.lang.String, java.lang.String)
	 */
	@Override
	public int updatePerfor(String performancelist, String artistid) {
		// TODO Auto-generated method stub
		return myInfoDao.updatePerfor(performancelist, artistid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyInfoService#updateBiographyList(java.lang.String, java.lang.String)
	 */
	@Override
	public int updateBiographyList(String biographylist, String artistid) {
		// TODO Auto-generated method stub
		return myInfoDao.updateBiographyList(biographylist, artistid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyInfoService#updateBiographyHighest(java.lang.String, java.lang.String)
	 */
	@Override
	public int updateBiographyHighest(String biographyhighest, String artistid) {
		// TODO Auto-generated method stub
		return myInfoDao.updateBiographyHighest(biographyhighest, artistid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyInfoService#updateSelfIntro(java.lang.String, java.lang.String)
	 */
	@Override
	public int updateSelfIntro(String selfintroduction, String artistid) {
		// TODO Auto-generated method stub
		return myInfoDao.updateSelfIntro(selfintroduction, artistid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyInfoService#updateSpecialty(java.lang.String, java.lang.String)
	 */
	@Override
	public int updateSpecialty(String specialty, String artistid) {
		// TODO Auto-generated method stub
		return myInfoDao.updateSpecialty(specialty, artistid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyInfoService#updateType(java.lang.String, java.lang.String)
	 */
	@Override
	public int updateType(String type, String artistid) {
		// TODO Auto-generated method stub
		return myInfoDao.updateType(type, artistid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyInfoService#updateMore(com.xunyanhui.bean.ArtistSelfInfo)
	 */
	@Override
	public int updateMore(ArtistSelfMoreInfo artistSelfInfo) {
		// TODO Auto-generated method stub
		return myInfoDao.updateMore(artistSelfInfo);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyInfoService#updateMinSalary(int, java.lang.String)
	 */
	@Override
	public int updateMinSalary(int minsalary, String artistid) {
		// TODO Auto-generated method stub
		return myInfoDao.updateMinSalary(minsalary, artistid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyInfoService#updateCid(java.lang.String, java.lang.String)
	 */
	@Override
	public int updateCidAndDeviceToken(String cid, String id,String devicetoken) {
		// TODO Auto-generated method stub
		return myInfoDao.updateCidAndDeviceToken(cid, id,devicetoken);
	}
	
	
	@Override
	public AuthState getAuthOfUser(String uid) {
		// TODO Auto-generated method stub
		return myInfoDao.getAuthOfUser(uid);
	}
	
	@Override
	public int getAuthStateOfUser(String uid) {
		// TODO Auto-generated method stub
		return myInfoDao.getAuthStateOfUser(uid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyInfoService#getArtistListOfAtten(java.lang.String)
	 */
	@Override
	public List<ArtistAttenListV> getArtistListOfAtten(String uid) {
		// TODO Auto-generated method stub
		return myInfoDao.getArtistListOfAtten(uid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.MyInfoService#updateAuthState(java.lang.String, int, java.lang.String, int, java.lang.String)
	 */
	@Override
	public int updateAuthState(String uid, int isauth, String auditresult,
			int authstate, String auditid) {
		// TODO Auto-generated method stub
		return myInfoDao.updateAuthState(uid, isauth, auditresult, authstate, auditid);
	}

}
