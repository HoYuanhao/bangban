/**
 * 创建日期：2016-12-20下午4:04:51
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyanhui.dao.ArtistOpusDao;
import com.xunyanhui.model.ArtistOpus;
import com.xunyanhui.service.ArtistOpusService;


@Service
public class ArtistOpusServiceImpl implements ArtistOpusService {
	final Logger logger = Logger.getLogger("ArtistOpusDao");
	@Autowired
	private ArtistOpusDao artistOpusDao;
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.ArtistOpusService#insertArtistOpus(com.xunyanhui.model.ArtistOpus)
	 */
	@Override
	public int insertArtistOpus(ArtistOpus artistOpus) {
		// TODO Auto-generated method stub
		int ret = artistOpusDao.insertArtistOpus(artistOpus);
		return ret;
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.ArtistOpusService#updateArtistOpusHomePic(com.xunyanhui.model.ArtistOpus)
	 */
	@Override
	public int updateArtistOpusHomePic(ArtistOpus artistOpus) {
		// TODO Auto-generated method stub
		int ret =artistOpusDao.updateArtistOpusHomePic(artistOpus);
		return ret;
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.ArtistOpusService#updateArtistOpus(com.xunyanhui.model.ArtistOpus)
	 */
	@Override
	public int updateArtistOpus(ArtistOpus artistOpus) {
		// TODO Auto-generated method stub
		int ret = artistOpusDao.updateArtistOpus(artistOpus);
		return ret;
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.ArtistOpusService#checkUidOfOpus(java.lang.String, java.lang.String)
	 */
	@Override
	public String checkUidOfOpus(String oid) {
		// TODO Auto-generated method stub
		return artistOpusDao.checkUidOfOpus(oid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.ArtistOpusService#updatePraise(java.lang.String, int)
	 */
	@Override
	public int updatePraise(String oid, int value) {
		// TODO Auto-generated method stub
		return artistOpusDao.updatePraise(oid, value);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.ArtistOpusService#updateGoodLevel(java.lang.String, int)
	 */
	@Override
	public int updateGoodLevel(String artistid, int praise) {
		// TODO Auto-generated method stub
		return artistOpusDao.updateGoodLevel(artistid, praise);
	}
	
	
	
	@Override
	public int incPlayNum(String oid) {
		// TODO Auto-generated method stub
		return artistOpusDao.incPlayNum(oid);
	}
	
}
