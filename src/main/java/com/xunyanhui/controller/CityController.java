/**
 * 创建日期：2017-1-11下午4:06:09
 * 修改日期：
 * 作者：邢传军
 * 处理城市列表
 */
package com.xunyanhui.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xunyanhui.model.City;
import com.xunyanhui.service.CityService;
@Controller
@RequestMapping("/")
public class CityController {
	/** 日志实例 */
	private static final Logger logger = Logger.getLogger(CityController.class);
	@Resource	
	CityService cityService;
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "city/getCityList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getCityList(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<City> cityList = cityService.getCityList();
		if (cityList != null && cityList.size() > 0) {
			List<City> cityListTmp = null;
			TreeMap<String, Object> cityMap = new TreeMap<String, Object>();
			for (int i = 0; i < cityList.size(); i++) {
				City city = new City();
				city = cityList.get(i);
				String firstChar = city.getPinyin().substring(0, 1);
				cityListTmp = (List<City>) cityMap.get(firstChar);
				if (cityListTmp == null) {
					cityListTmp = new ArrayList<City>();
				}
				cityListTmp.add(city);
				cityMap.put(firstChar, cityListTmp);
			}
			result.put("cityList", cityMap);
			result.put("msg", "获取城市目录成功");
			result.put("code", "1");
		}
		else{
			result.put("msg", "无城市目录！");
			result.put("code", "0");
		}
		
		return JSONObject.fromObject(result).toString();
	}
	/*
	 * 根据传入的地址来返回所在的城市，符合条件的城市列表
	 */
	
	@RequestMapping(value = "city/getCityName/{address}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getCityName(HttpServletRequest request,
			@PathVariable("address") String address) {
		Map<String, Object> result = new HashMap<String, Object>();
	    HttpSession session =request.getSession();
		List<City> cityList = cityService.getCityList();
		int isFind = 0;
		List<String> citys = new ArrayList<String>();
		for(int i=0;i<cityList.size();i++){
			if(address.contains(cityList.get(i).getName()+"市")){
				citys.add(cityList.get(i).getName());
				session.setAttribute("ccity", cityList.get(i).getName());
				isFind++;
			}
		}
		if(isFind==1){
			result.put("cityList", citys);
			result.put("msg", "城市目录成功");
			result.put("code", "1");
		}else{
			result.put("msg", "请手动定位附近城市");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
//	   @RequestMapping("/city")
//	   public @ResponseBody
//	   String getcity(@RequestParam("city") String city,
//			   HttpServletRequest request){
//		   HttpSession session =request.getSession();
//		   User user =(User) session.getAttribute("user");
//			   session.setAttribute("ccity", city);
//		  
//		   
//		   return "{\"code\":1}";
//		   
//	   }

}
