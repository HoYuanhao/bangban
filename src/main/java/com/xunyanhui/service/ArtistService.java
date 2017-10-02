package com.xunyanhui.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xunyanhui.bean.ArtistListV;
import com.xunyanhui.bean.HomeArtist;
import com.xunyanhui.bean.HomePageBean;
import com.xunyanhui.model.ArtistDetailView;
import com.xunyanhui.model.User;

public interface ArtistService {
	public final static int NORMAL = 0;// 正常

	public final static int COMPLETE_NUM_DESC = 1;// 完成数从高到低
	public final static int MOST_POPULAR_DESC = 2;// 最受欢迎
	public final static int AUTH_DESC = 3;// 实名认证

	public final static int FAN_NUM_DESC = 4;// 人气从高到低
	public final static int FAN_NUM_ASC = 5;// 人气从低到高
	public final static int SALARY_DESC = 6;// 价格从高到低
	public final static int SALARY_ASC = 7;// 价格从低到高
	public final static int RATE_OF_PRAISE_DESC = 8;// 好评从高到低

	public final static int WOMAN = 2;
	public final static int MEN = 1;
	/**
	 * 获取用户定位
	 */
    public void updateLatAndLngById(String id,String lat,String lng);
	/**
	 * 获取主页json的业务方法
	 * 
	 * @return 主页数据
	 */
	public HomePageBean getHomePage(Date date, int page,HttpServletRequest request);

	/**
	 * 获取主页推荐艺人分页列表
	 * 
	 * @param date
	 *            时间戳
	 * @param page
	 *            要获取的页数
	 * @return 返回推荐艺人的列表
	 */
	public List<HomeArtist> getreCommendArtistList(Date date, int page,HttpServletRequest request);

	/**
	 * 获取艺人详细信息，当前用户登录
	 * 
	 * @param id
	 *            艺人id
	 * 
	 * @return 返回艺人的详细信息
	 */
	public ArtistDetailView getArtistById(String id, String uid);
	/**
	 * 获取艺人详细信息，当前用户未登录
	 * 
	 * @param id
	 *            艺人id
	 * 
	 * @return 返回艺人的详细信息
	 */
	public ArtistDetailView getAtristByIdUnLogin(String id,String uid);

	public List<ArtistListV> getArtiArtistList(Date date, int order,
			String type, int gender, int page,HttpServletRequest request);

	public String follow(String perfId, User user);

	public List<ArtistListV> search(String key, Date date, int page,HttpServletRequest request);

}
