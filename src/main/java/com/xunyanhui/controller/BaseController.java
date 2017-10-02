package com.xunyanhui.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.ResponseBody;

import com.xunyanhui.bean.HomeArtist;
import com.xunyanhui.bean.HomePageBean;
import com.xunyanhui.dao.CityDao;
import com.xunyanhui.service.ArtistService;


/**
 * 首页视图Controler
 * 
 * @author 柯鑫
 * 
 */
@Controller
@RequestMapping("/base")
@RequiresRoles("user")
public class BaseController {
	@Autowired
	private ArtistService artistService;
    @Autowired
    private CityDao citydao;
	final Logger logger = Logger.getLogger("BaseController");

	/**
	 * 该接口负责首页数据的初始化
	 */
	@ResponseBody
	@RequestMapping("/home")
	public HomePageBean getHomePage(HttpServletRequest request,
			@RequestParam("lng")Double lng,
			@RequestParam("lat")Double lat) {
		logger.debug("/base/home 获取主页数据");
		
		HttpSession httpsession =request.getSession();
		httpsession.setAttribute("lat", lat);
		httpsession.setAttribute("lng", lng);
		Subject subject = SecurityUtils.getSubject();
		System.out.println("getHomePage 是否有权限:" + subject.hasRole("user"));
		Session session = subject.getSession(true);
		Date date = new Date();
		session.setAttribute("homeDate", date);
		HomePageBean homePage = artistService.getHomePage(date, 1,request);
		logger.debug("获取到的主页数据" + homePage);
		return homePage;
	}

	/**
	 * 首页推荐艺人的翻页功能
	 */
	@ResponseBody
	@RequestMapping("/home/artist/{page}")
	public List<HomeArtist> CommendArtistList(@PathVariable("page") int page,
			HttpServletRequest request) {
		logger.debug("/base/home 获取获取推荐艺人列表\tpage = " + page);
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		Date date = (Date) session.getAttribute("homeDate");
		List<HomeArtist> homeArtist = artistService.getreCommendArtistList(
				date, page,request);
		logger.debug("获取到的主页数据" + homeArtist);
		//logger.debug("当前的date为   " + date.getTime());
		return homeArtist;
	}

   


}
