package com.xunyanhui.controller;

//import com.citic.test.entity.Person;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunyanhui.bean.ArtistListV;
import com.xunyanhui.model.ArtistDetailView;
import com.xunyanhui.model.ArtistOpusV;
import com.xunyanhui.model.User;
import com.xunyanhui.service.ArtistService;
import com.xunyanhui.service.UserService;

/**
 * 基于Restful风格架构的艺人管理
 * 
 * @author xingchuanjun
 * @version V1.0
 * @history 2016-12-1 下午20:38:12 xingchuanjun 修改
 */

@Controller
@RequestMapping(value = "/artist")
public class ArtistController {

	/** 日志实例 */
	private static final Logger logger = Logger.getLogger("ArtistController");
	private static final HashMap<String, String> mapType = new HashMap<String , String>(){
	private static final long serialVersionUID = 947020597857226007L;

	{  
		 put("1", "搬家");  
	     put("2", "家政");  
	     put("3", "婚庆");
	     put("4", "教育");
	     put("5", "家装");
	     put("6", "回收");
	     put("7", "临促");
	     put("8", "制作");
	     put("9", "维修");
	     put("10","医疗");
	     put("11","跑腿");
	     put("12","伴游");
	     put("13","代办");
	     put("14","小时工");
	 }};  

	@Autowired
	private ArtistService artistService;

	/*
	 * 艺人列表的分页查询(匿名方式) 基本条件：时间戳；页号；页大小； 过滤条件：艺名；类型；性别； 排序条件：成交量；好评率；实名认证；出场费；关注数
	 * 过滤条件 {[ {"key":"name","val":"金池"}, {"key":"type","val":"歌手"},
	 * {"key":"sex","val":"男"} ]} 排序条件(排序中的0表示不参与排序，1表示升序，2表示降序) {[
	 * {"key":"chengjiao","val":1}, {"key":"haoping","val":2},
	 * {"key":"shiming","val":0}], {"key":"chuchangfei","val":1}],
	 * {"key":"guanzhu","val":0}]}
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<ArtistListV> getArtistList(
			@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "order", required = false, defaultValue = "0") int order,
			@RequestParam(value = "gender", required = false, defaultValue = "0") int gender,
			HttpServletRequest request) {
		
		List<ArtistListV> artiArtistList = null;
		Date d = new Date();
		Search search = new Search(order, type, gender, key, d);
		
		HttpSession session = request.getSession();
		
		logger.info("search in sessin"+search.toString());
		session.setAttribute("artistSearch", search);
		if (key != null && !key.equals("")) {
			artiArtistList = artistService.search(key, d, 1,request);
		} else {
			String artist_type = mapType.get(type);
			
			if(artist_type!=null){
				artist_type = "%"+artist_type+"%";
				
			}
			artiArtistList = artistService.getArtiArtistList(d, order, artist_type,
					gender, 1,request);
		}
		return artiArtistList;
	}

	@RequestMapping(value = "list/{page}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody 
	List<ArtistListV> getArtistPage(@PathVariable("page") int page, 
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Search search=null;
		 search = (Search) session.getAttribute("artistSearch");
		if(search==null)
			logger.error("search out session error");
		List<ArtistListV> artiArtistList = null;
		if (search.key!= null && !search.key.equals("")) {
			artiArtistList = artistService.search(search.key, search.date, page,request);
		} else {
			String artist_type = mapType.get(search.type);
			artiArtistList = artistService.getArtiArtistList(search.date,
					search.order, artist_type, search.gender, page,request);
		}
		return artiArtistList;
	}

	@RequestMapping(value = "/getArtiseById/{token}/{uid}/{id}.json", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	Object getArtiseById(@PathVariable("uid") String uid,
			@PathVariable("id") String id,
			HttpServletRequest request) {
		logger.info("读取艺人详情信息艺人id=" + id);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		ArtistDetailView artistDetailView = null;
		if (user != null) {
			System.out.println("denglu");
			artistDetailView = artistService
				.getArtistById(id, user.getId());
		}else{
			artistDetailView = artistService
			.getAtristByIdUnLogin(id, "ffd");
			
		}
		List<ArtistOpusV> artistOpusList = artistDetailView.getArtistOpusList();
		if(artistOpusList!=null){
			for(int i=artistOpusList.size()-1;i>=0;i--){
				String date = artistOpusList.get(i).getReleaseTime();
				if(date!=null&&date!=""){
					artistOpusList.get(i).setReleaseTime(date.substring(0, 10));
				}
				else{
					artistOpusList.remove(i);
					//i--;
				}
			}
		}
		return JSONObject.fromObject(artistDetailView).toString();
	}

	@RequestMapping(value = "/getArtiseById/{token}/{uid}/{id}.json", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String addAttention(@PathVariable("token") String token,
			@PathVariable("uid") String uid, 
			@PathVariable("id") String id) {

		return null;
	}

	@RequestMapping("/follow/{perfId}")
	@ResponseBody
	public String follow(@PathVariable("perfId") String perfId,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		String result = artistService.follow(perfId, user);
		return result;
	}

	private class Search {
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Search [date=" + date + ", key=" + key + ", order=" + order
					+ ", type=" + type + ", gender=" + gender + "]";
		}
		public Search(int order, String type, int gender, String key, Date date) {
			this.date = date;
			this.key = key;
			this.order = order;
			this.type = type;
			this.gender = gender;
		}
		
		Date date;
		String key;
		int order;
		String type;
		int gender;
	}

}
