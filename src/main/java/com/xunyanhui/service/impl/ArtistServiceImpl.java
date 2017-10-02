package com.xunyanhui.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyanhui.bean.ArtistListV;
import com.xunyanhui.bean.Distance;
import com.xunyanhui.bean.HomeArtist;
import com.xunyanhui.bean.HomePageBean;
import com.xunyanhui.dao.ArtistDao;
import com.xunyanhui.dao.DistanceDao;
import com.xunyanhui.dao.SystemDao;
import com.xunyanhui.dao.UserDao;
import com.xunyanhui.model.Artist;
import com.xunyanhui.model.ArtistDetailView;
import com.xunyanhui.model.HomePic;
import com.xunyanhui.model.User;
import com.xunyanhui.service.ArtistService;
import com.xunyanhui.utils.DistanceUtil;

@Service
public class ArtistServiceImpl implements ArtistService {
	private static final int HOME_PAGE_COUNT = 5;
	private static final int ARTIST_PAGE_COUNT = 5;

	final Logger logger = Logger.getLogger("ArtistService");
	@Autowired
	private UserDao userDao;
	@Autowired
	private SystemDao systemDao;
	@Autowired
	private ArtistDao artistDao;
	@Autowired
	private DistanceDao distanceDao;

	@Override
	public HomePageBean getHomePage(Date date, int page, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<HomePic> homePicList = systemDao.getHomePicList();
		List<User> homeArtistList = userDao.getHomeArtistList(date, (page - 1) * HOME_PAGE_COUNT, HOME_PAGE_COUNT);
		com.xunyanhui.bean.HomePic hp = null;
		List<com.xunyanhui.bean.HomePic> hpList = new ArrayList<com.xunyanhui.bean.HomePic>();
		for (HomePic homePic : homePicList) {
			hp = new com.xunyanhui.bean.HomePic();
			hp.setId(homePic.getId());
			hp.setPicPath(homePic.getPicPath());
			hpList.add(hp);
		}

		HomeArtist ha = null;
		HttpSession session = request.getSession();
		Double lat2 ;
		Double lng2 ;
		Double lat1 = (Double) session.getAttribute("lat");
		Double lng1 = (Double) session.getAttribute("lng");
		if (lat1 == null || lng1 == null) {
			lat1 = 0.0;
			lng1 = 0.0;
		}

		List<HomeArtist> haList = new ArrayList<HomeArtist>();
		for (User user : homeArtistList) {
			ha = new HomeArtist();
			Artist ar = user.getArtist();
			ha.setId(user.getId());
			ha.setAddress(ar.getAddress());
			ha.setCompleteNum(ar.getCompleteNum());
			ha.setFanNum(ar.getFanNum());
			ha.setSpecialty(ar.getSpecialty());
			ha.setStageName(ar.getStageName());
			ha.setMinSalary(ar.getMinSalary().toString());
			ha.setPic(ar.getPic());

			List<Distance> distancelist = distanceDao.getUserLatAndLngById(user.getId());
			lat2 = distancelist.get(0).getLat();
			lng2 = distancelist.get(0).getLng();
			if (lat1 == 0.0 || lng1 == 0.0) {
				lat2 = 0.0;
				lng2 = 0.0;
			}
			if(lat2==null||lng2==null){
				lat2=0.0;
				lng2=0.0;
				lng1=0.0;
				lat1=0.0;
			}
			int distance = (int) DistanceUtil.GetDistance(lng1, lat1, lng2, lat2);
			ha.setDistance(distance);

			haList.add(ha);

		}
		HomePageBean pageBean = new HomePageBean();
		pageBean.setHomePic(hpList);
		pageBean.setArtist(haList);
		return pageBean;
	}

	@Override
	public List<HomeArtist> getreCommendArtistList(Date date, int page, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<User> homeArtistList = userDao.getHomeArtistList(date, (page - 1) * HOME_PAGE_COUNT + 1, HOME_PAGE_COUNT);
		HomeArtist ha = null;
		HttpSession session = request.getSession();
		Double lat2 ;
		Double lng2 ;
		Double lat1 = (Double) session.getAttribute("lat");
		Double lng1 = (Double) session.getAttribute("lng");
		if (lat1 == null || lng1 == null) {
			lat1 = 0.0;
			lng1 = 0.0;
		}

		List<HomeArtist> haList = new ArrayList<HomeArtist>();
		for (User user : homeArtistList) {
			ha = new HomeArtist();
			Artist ar = user.getArtist();
			ha.setId(user.getId());
			ha.setAddress(ar.getAddress());
			ha.setCompleteNum(ar.getCompleteNum());
			ha.setFanNum(ar.getFanNum());
			ha.setSpecialty(ar.getSpecialty());
			ha.setStageName(ar.getStageName());
			ha.setMinSalary(ar.getMinSalary().toString());

			List<Distance> distancelist = distanceDao.getUserLatAndLngById(user.getId());
			lat2 = distancelist.get(0).getLat();
			lng2 = distancelist.get(0).getLng();
			if (lat1 == 0.0 || lng1 == 0.0) {
				lat2 = 0.0;
				lng2 = 0.0;
			}if(lat2==null||lng2==null){
				lat2=0.0;
				lng2=0.0;
			}
			int distance = (int) DistanceUtil.GetDistance(lng1, lat1, lng2, lat2);
			ha.setDistance(distance);

			haList.add(ha);

		}

		return haList;
	}

	@Override
	public List<ArtistListV> getArtiArtistList(Date date, int order, String type, int gender, int page,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<User> userList = userDao.getArtiArtistList(date, order, type, gender, (page - 1) * ARTIST_PAGE_COUNT,
				ARTIST_PAGE_COUNT);
		ArtistListV artist = null;
		List<ArtistListV> artistList = new ArrayList<ArtistListV>();
		HttpSession session = request.getSession();
		Double lat2 ;
		Double lng2 ;
		Double lat1 = (Double) session.getAttribute("lat");
		Double lng1 = (Double) session.getAttribute("lng");
		if (lat1 == null || lng1 == null) {
			lat1 = 0.0;
			lng1 = 0.0;
		}
		
		for(User user:userList) {
			artist = new ArtistListV();
			Artist a = user.getArtist();
			artist.setId(a.getId());
			artist.setCompleteNum(a.getCompleteNum());
			artist.setFanNum(a.getFanNum());
			artist.setMinSalary(a.getMinSalary());
			artist.setStageName(a.getStageName());
			artist.setV(user.isV());
			artist.setCity(a.getCity());

			artist.setIsAuth(a.getIsAuth());
			artist.setHonestyLevel(a.getHonestyLevel());
			artist.setPerformanceType(a.getPerformanceTypeId());
            
			List<Distance> distancelist = distanceDao.getUserLatAndLngById(a.getId());
			lat2 = distancelist.get(0).getLat();
			lng2 = distancelist.get(0).getLng();
			if (lat1 == 0.0 || lng1 == 0.0) {
				lat2 = 0.0;
				lng2 = 0.0;
			}if(lat2==null||lng2==null){
				lat2=0.0;
				lng2=0.0;
				lat1=0.0;
				lng1=0.0;
			}
			int distance = (int) DistanceUtil.GetDistance(lng1, lat1, lng2, lat2);
			artist.setDistance(distance);
			artistList.add(artist);
		}
		return artistList;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xunyanhui.service.ArtistService#getArtistById(java.lang.String)
	 */
	@Override
	public ArtistDetailView getArtistById(String id, String uid) {
		ArtistDetailView list = artistDao.getAtristById(id, uid);
		return list;
	}

	@Override
	public String follow(String perfId, User user) {
		// TODO Auto-generated method stub
		String result = null;
		if (user == null) {
			result = "{\"code\":-1}";
		} else if (true) {

		}
		return result;
	}

	@Override
	public List<ArtistListV> search(String key, Date date, int page, HttpServletRequest request) {
		// TODO Auto-generated method stub
		/*
		 * if (key == null || key.equals("")) { return null; }
		 */
		List<User> userList = userDao.searchByKey("%" + key + "%", date, (page - 1) * ARTIST_PAGE_COUNT,
				ARTIST_PAGE_COUNT);
		ArtistListV artist = null;
		List<ArtistListV> artistList = new ArrayList<ArtistListV>();
		HttpSession session = request.getSession();
		Double lat2 ;
		Double lng2 ;
		Double lat1 = (Double) session.getAttribute("lat");
		Double lng1 = (Double) session.getAttribute("lng");
		if (lat1 == null || lng1 == null) {
			lat1 = 0.0;
			lng1 = 0.0;
		}

		if (userList != null) {
			for (User user : userList) {
				artist = new ArtistListV();
				Artist a = user.getArtist();
				artist.setId(a.getId());
				artist.setCompleteNum(a.getCompleteNum());
				artist.setFanNum(a.getFanNum());
				artist.setMinSalary(a.getMinSalary());
				artist.setStageName(a.getStageName());
				artist.setV(user.isV());
				artist.setPerformanceType(a.getPerformanceTypeId());
				artist.setIsAuth(a.getIsAuth());
				artist.setHonestyLevel(a.getHonestyLevel());
				artist.setCity(a.getCity());
				artist.setPerformanceType(a.getPerformanceTypeId());

				List<Distance> distancelist = distanceDao.getUserLatAndLngById(a.getId());
				lat2 = distancelist.get(0).getLat();
				lng2 = distancelist.get(0).getLng();
				if (lat1 == 0.0 || lng1 == 0.0) {
					lat2 = 0.0;
					lng2 = 0.0;
				}
				int distance = (int) DistanceUtil.GetDistance(lng1, lat1, lng2, lat2);
				artist.setDistance(distance);

				artistList.add(artist);
			}
		}
		return artistList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xunyanhui.service.ArtistService#getAtristByIdUnLogin(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public ArtistDetailView getAtristByIdUnLogin(String id, String uid) {
		ArtistDetailView list = artistDao.getAtristByIdUnLogin(id, uid);

		return list;
	}

	@Override
	public void updateLatAndLngById(String id, String lat, String lng) {
		artistDao.updateLatAndLngById(id, lat, lng);

	}

}
