package com.xunyanhui.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunyanhui.bean.Distance;
import com.xunyanhui.bean.FileSaveRet;
import com.xunyanhui.bean.PerfBean;
import com.xunyanhui.bean.PerfBeanImg;
import com.xunyanhui.bean.PerforBean;
import com.xunyanhui.bean.Performance;
import com.xunyanhui.bean.PerformanceOfEntry;

import com.xunyanhui.dao.DistanceDao;
import com.xunyanhui.jms.bean.MessageInfo;
import com.xunyanhui.jms.service.JMSProducerTwo;

import com.xunyanhui.model.EntryRecordN;
import com.xunyanhui.model.User;
import com.xunyanhui.service.MyPerformanceService;
import com.xunyanhui.service.PerformanceService;
import com.xunyanhui.service.UserService;
import com.xunyanhui.utils.DistanceUtil;
import com.xunyanhui.utils.FileUtil;
import com.xunyanhui.utils.UUidUtil;

@Controller
@RequestMapping("/perf")
public class PerformanceControler {

	/** 日志实例 */
	private static final Logger logger = Logger.getLogger("PerformanceControler");
	// 获取配置中的演艺活动(列表)文件上传路径和网络访问位置
	private @Value("#{test['REPOSITORY_PATH_PERFORMANCE']}") String head_upload_path;
	private @Value("#{test['IMAGE_BASE_URL_PERFORMANCE']}") String head_get_path;
	// 获取配置中的演艺活动(封面)文件上传路径和网络访问位置
	private @Value("#{test['REPOSITORY_PATH_PERFORMANCE_HOME']}") String head_home_upload_path;
	private @Value("#{test['IMAGE_BASE_URL_PERFORMANCE_HOME']}") String head_home_get_path;

	// 获取配置中的文件的临时中转路径
	private @Value("#{test['REPOSITORY_PATH_TEMP']}") String file_tmp;
	@Autowired
	private PerformanceService performanceService;
	@Autowired
	private MyPerformanceService myPerformanceService;
	@Resource
	private JMSProducerTwo jMSProducerTwo;
	@Resource
	private ActiveMQQueue myQueueTwo;
	@Autowired
	private DistanceDao distanceDao;


	@RequestMapping("list.json")
	@ResponseBody
	public List<PerforBean> getPerformanceFirstPage(@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "order", required = false, defaultValue = "0") int order,
			@RequestParam(value = "type", required = false) String type, 
			HttpServletRequest request,
			@RequestParam(value="lat",required = false) Double lat, 
			@RequestParam(value="lng",required = false) Double lng) {
		System.out.println("2222key:" + key + "order" + order + "type" + type);
		HttpSession session = request.getSession();
		session.setAttribute("lat", lat);
		session.setAttribute("lng", lng);
		Search s = new Search();
		s.date = new Date();
		s.order = order;

		if (type == null || type.equals("0")) {
			type = null;

		}

		s.type = type;
		s.key = key;
		session.setAttribute("perfSearch", s);
		logger.error("performance  list first");
		String uid = null;
		Subject subject = SecurityUtils.getSubject();
		boolean isLogin = subject.hasRole("user");
		List<PerforBean> performanceList = null;
		Double lat2 = null;
		Double lng2 = null;
		User user = null;
		String ccity = null;
		if (isLogin) {
			user = (User) session.getAttribute("user");
			uid = user.getId();
		}
		ccity = (String) session.getAttribute("ccity");
		System.out.println("ccity:"+ccity);
		if (key != null && !key.equals("")) {

			performanceList = performanceService.searchByKey(s.date, "%" + s.key + "%", 1,uid, ccity);
			for (PerforBean perforbean : performanceList) {
				List<Distance> distancelist = distanceDao.getBangbanLatAndLngById(perforbean.getId());
				lat2 = distancelist.get(0).getLat();
				lng2 = distancelist.get(0).getLng();
				if (lat2 == null || lng2 == null) {
					lat2 = 0.0;
					lng2 = 0.0;
				}
				int distance = (int) DistanceUtil.GetDistance(lng, lat, lng2, lat2);
				perforbean.setDistance(distance);
			}
		} else {
			performanceList = performanceService.getPerformanceList(s.date, order, type, 1,uid,ccity);
			if (user != null) {
				for (PerforBean perforbean : performanceList) {
					List<Distance> distancelist = distanceDao.getBangbanLatAndLngById(perforbean.getId());
					lat2 = distancelist.get(0).getLat();
					lng2 = distancelist.get(0).getLng();
					if (lat2 == null || lng2 == null) {
						lat2 = 0.0;
						lng2 = 0.0;
					}
					int distance = (int) DistanceUtil.GetDistance(lng, lat, lng2, lat2);
					perforbean.setDistance(distance);
					
				}
			}
		}
		
		
		return performanceList;
	}

	@RequestMapping("list/{page}.json")
	@ResponseBody
	public List<PerforBean> getPerformancePage(@PathVariable(value = "page") int page, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Search s = (Search) session.getAttribute("perfSearch");
		if (s == null) {
			return null;
		}
		String ccity = null;
		String uid = null;
		Subject subject = SecurityUtils.getSubject();
		boolean isLogin = subject.hasRole("user");
		User user = null;
		if (isLogin) {
			user = (User) session.getAttribute("user");
			uid = user.getId();
		}
			ccity = (String) session.getAttribute("ccity");
		Double lat1 = null ;
		Double lng1 = null ;
		Double lat2 ;
		Double lng2 ;
		if (user != null) {
			lat1 = (Double) session.getAttribute("lat");
			lng1 = (Double) session.getAttribute("lng");
			if (lat1 == null || lng1 == null) {
				lat1 = 0.0;
				lng1 = 0.0;
			}
		}
		logger.error("performance :page" + page);
		List<PerforBean> performanceList = null;
		if (s.key != null && !s.key.equals("")) {

			performanceList = performanceService.searchByKey(s.date, "%" + s.key + "%", page, uid,ccity);
			if (user != null) {
				for (PerforBean perforbean : performanceList) {
					List<Distance> distancelist = distanceDao.getBangbanLatAndLngById(perforbean.getId());
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
					perforbean.setDistance(distance);
				}
			
		} else {
			
				ccity = (String) session.getAttribute("ccity");
			

			performanceList = performanceService.getPerformanceList(s.date, s.order, s.type, page, uid,ccity);
			
				for (PerforBean perforbean : performanceList) {
					List<Distance> distancelist = distanceDao.getBangbanLatAndLngById(perforbean.getId());
					lat2 = distancelist.get(0).getLat();
					lng2 = distancelist.get(0).getLng();
					if (lat2 == null || lng2 == null) {
						lat2 = 0.0;
						lng2 = 0.0;
					}
					int distance = (int) DistanceUtil.GetDistance(lng1, lat1, lng2, lat2);
					perforbean.setDistance(distance);
				}
			}
		}
		return performanceList;
	}

	@RequestMapping(value = "/sendPerf", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String sendPerformance(HttpServletRequest request, @RequestBody PerfBeanImg perfBeanimg) {
		HttpSession session = request.getSession();
		PerfBean perfBean1 = perfBeanimg.getPerfBean();
		String base64 = perfBeanimg.getPath();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		String result = null;
		if (user != null) {
			List<String> path = new ArrayList<String>();
			path.add(this.getHead_home_upload_path());
			path.add(this.getHead_upload_path());
			String pid = UUidUtil.getUUid();
			FileSaveRet fileRet = FileUtil.saveAsBase64(base64, path, pid);
			perfBean1.setUid(user.getId());
			perfBean1.setReleaseTime(new Date());
			if (fileRet.getCode() == 1) {
				if (performanceService.addPerformance(perfBean1, pid, fileRet.getPic_extra())) {
					MessageInfo msgInfo = new MessageInfo();
					/*
					 * {"type":"3","sid":"sid","did":"did","value":"消息内容"}
					 * Sid表示演艺id did表示类别，value表示演艺标题
					 */
					msgInfo.setType(3);
					msgInfo.setSid(pid);
					msgInfo.setValue(perfBean1.getTitle());
					msgInfo.setDid(Integer.valueOf(perfBean1.getType()).toString());
					jMSProducerTwo.sendMessage(myQueueTwo, JSONObject.fromObject(msgInfo).toString());
					result = "{\"msg\":\"成功\",\"code\":\"1\"}";
				} else {
					result = "{\"msg\":\"失败\",\"code\":\"0\"}";
				}
			} else {
				result = "{\"msg\":\"" + fileRet.getMsg() + "\",\"code\":\"" + fileRet.getCode() + "\"}";
			}

		} else {
			result = "{\"msg\":\"登录失效\",\"code\":\"-1\"}";
		}
		System.out.println(result);
		return result;
	}

	@RequestMapping("getPerf/{perforId}")
	@ResponseBody
	public Performance getPerformance(@PathVariable("perforId") String perforId, HttpServletRequest request) {
		Performance performance = performanceService.getPerformance(perforId, request);

		return performance;
	}

	/*
	 * 获取我报名的演艺活动详情
	 */
	@RequestMapping(value = "getPerfOfEntry/{perforId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getPerfOfEntry(@PathVariable("perforId") String perforId, HttpServletRequest request) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if (user != null) {
			EntryRecordN entryRecord = performanceService.getEntryRecord(user.getId(), perforId);
			if (entryRecord != null) {
				PerformanceOfEntry perOfEntry = new PerformanceOfEntry();
				Performance perf = performanceService.getPerformance(perforId, request);
				if (perf != null) {
					perOfEntry.setId(perf.getId());
					perOfEntry.setTitle(perf.getTitle());
					perOfEntry.setCity(perf.getCity());
					perOfEntry.setType(perf.getType());
					perOfEntry.setTime(perf.getTime());
					perOfEntry.setTime_end(perf.getTime_end());
					perOfEntry.setArtistList(perf.getArtistList());
					perOfEntry.setSignupNum(perf.getSignupNum());
					perOfEntry.setLanguage(perf.getLanguage());
					perOfEntry.setContacts(perf.getContacts());
					perOfEntry.setContactway(perf.getContactway());
					perOfEntry.setSalary(perf.getSalary());
					perOfEntry.setNumberOfApplicants(perf.getNumberOfApplicants());
					perOfEntry.setGender(perf.getGender());
					perOfEntry.setStature(perf.getStature());
					perOfEntry.setRequire(perf.getRequire());
					perOfEntry.setSalaryDescription(perf.getSalaryDescription());
					perOfEntry.setPic(perf.getPic());
					perOfEntry.setSelf(false);
					perOfEntry.setArtistid(user.getId());
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					perOfEntry.setEntryTime(df.format(entryRecord.getSigupTime()));
					perOfEntry.setPrice(entryRecord.getPrice());
					perOfEntry.setDesc(entryRecord.getDescription());
					if (entryRecord.getState() == 4) {
						perOfEntry.setSignup(false);
					} else {
						perOfEntry.setSignup(true);
					}

					retMap.put("perfor", perOfEntry);
					retMap.put("msg", "数据读取成功！");
					retMap.put("code", "1");

				} else {
					retMap.put("msg", "数据信息不符！");
					retMap.put("code", "-2");
				}
			} else {
				retMap.put("msg", "数据信息不符！");
				retMap.put("code", "-2");
			}
		} else {
			retMap.put("msg", "未登录");
			retMap.put("code", "-1");
		}

		return JSONObject.fromObject(retMap).toString();
	}

	/**
	 * 演艺报名 peformanceId 报名的演艺Id price 报名价格 desc 报名说明
	 * 
	 * @return
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String performanceSignup(@RequestParam("peformanceId") String peformanceId,
			@RequestParam("price") String price, @RequestParam("desc") String desc, HttpServletRequest request) {
		System.out.println("performId:" + peformanceId + ",price:" + price + ",desc:" + desc);
		Map<String, Object> retMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if (user != null) {
			int is_Artist = myPerformanceService.isArtist(user.getId());
			System.out.println("is_Artist" + is_Artist + ",uid:" + user.getId());
			if (is_Artist == 1) {
				Integer i_price;
				try {
					i_price = Integer.valueOf(price);
				} catch (NumberFormatException e) {
					retMap.put("msg", "数据异常");
					retMap.put("code", "-2");
					System.out.println(retMap);
					return JSONObject.fromObject(retMap).toString();
				}

				int result = performanceService.performanceSignup(user, peformanceId, i_price, desc);
				switch (result) {
				case 2:
					retMap.put("msg", "不允许重复报名");
					retMap.put("code", "2");
					System.out.println(retMap);
					break;
				case 3:
					retMap.put("msg", "不允许自己报名");
					retMap.put("code", "3");
					System.out.println(retMap);
					break;
				case 1:
					retMap.put("msg", "报名成功");
					retMap.put("code", "1");
					System.out.println(retMap);
					break;
				default:
					retMap.put("msg", "其他未知错误");
					retMap.put("code", "4");
					System.out.println(retMap);
					break;
				}
			} else {
				retMap.put("msg", "当前用户尚未成为艺人");
				retMap.put("code", "5");
				System.out.println(retMap);
			}

		} else {
			retMap.put("msg", "未登录");
			retMap.put("code", "-1");
			System.out.println(retMap);
		}

		return JSONObject.fromObject(retMap).toString();
	}

	/**
	 * 邀请艺人参加演艺活动，只演艺活动发布人，主动邀请符合需要的艺人参见演艺活动 系统在报名表中创建一个表名信息，并将该报名状态值设置为8——即邀请报名
	 * 
	 * @return
	 */
	@RequestMapping(value = "/invite/{artistid}/{performanceId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String performanceInvite(@PathVariable("artistid") String artistid,
			@PathVariable("performanceId") String performanceId, HttpServletRequest request) {
		System.out.println("performId:" + performanceId + ",artistid:" + artistid);
		Map<String, Object> retMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if (user != null) {
			int is_Artist = myPerformanceService.isArtist(artistid);
			System.out.println("is_Artist" + is_Artist + ",uid:" + user.getId());
			if (is_Artist == 1) {
				int result = performanceService.performanceInvite(artistid, performanceId, user.getId());
				switch (result) {
				case 2:
					retMap.put("msg", "不允许重复邀请或以报名");
					retMap.put("code", "2");
					break;
				case 3:
					retMap.put("msg", "演艺活动不存在");
					retMap.put("code", "3");
					break;
				case 1:
					retMap.put("msg", "邀请成功");
					retMap.put("code", "1");
					break;
				default:
					retMap.put("msg", "其他未知错误");
					retMap.put("code", "4");
					break;
				}
			} else {
				retMap.put("msg", "当前用户尚未成为艺人");
				retMap.put("code", "5");
			}

		} else {
			retMap.put("msg", "未登录");
			retMap.put("code", "-1");
		}
		return JSONObject.fromObject(retMap).toString();
	}

	/**
	 * 接受报名邀请，邀请后把报名状态修改为0，表示报名待成交，成交由发布人进行最后确认 演艺报名 peformanceId 报名的演艺Id price
	 * 报名价格 desc 报名说明
	 * 
	 * @return
	 */
	@RequestMapping(value = "/enableInvite/{performanceId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String enableInvite(@PathVariable("performanceId") String performanceId, @RequestParam("price") String price,
			@RequestParam("desc") String desc, HttpServletRequest request) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if (user != null) {
			int is_Artist = myPerformanceService.isArtist(user.getId());
			System.out.println("is_Artist" + is_Artist + ",uid:" + user.getId());
			if (is_Artist == 1) {
				Integer i_price;
				try {
					i_price = Integer.valueOf(price);
				} catch (NumberFormatException e) {
					retMap.put("msg", "数据异常");
					retMap.put("code", "-2");
					System.out.println(retMap);
					return JSONObject.fromObject(retMap).toString();
				}
				int result = performanceService.enableInvite(user.getId(), performanceId, i_price, desc);
				switch (result) {
				case 2:
					retMap.put("msg", "报名状态异常");
					retMap.put("code", "2");
					System.out.println(retMap);
					break;
				case 3:
					retMap.put("msg", "尚未发出邀请");
					retMap.put("code", "3");
					System.out.println(retMap);
					break;
				case 1:
					retMap.put("msg", "接受邀请成功");
					retMap.put("code", "1");
					System.out.println(retMap);
					break;
				default:
					retMap.put("msg", "其他未知错误");
					retMap.put("code", "4");
					System.out.println(retMap);
					break;
				}
			} else {
				retMap.put("msg", "当前用户尚未成为艺人");
				retMap.put("code", "5");
				System.out.println(retMap);
			}

		} else {
			retMap.put("msg", "未登录");
			retMap.put("code", "-1");
			System.out.println(retMap);
		}
		return JSONObject.fromObject(retMap).toString();
	}

	public String getHead_upload_path() {
		return head_upload_path;
	}

	public void setHead_upload_path(String head_upload_path) {
		this.head_upload_path = head_upload_path;
	}

	public String getHead_get_path() {
		return head_get_path;
	}

	public void setHead_get_path(String head_get_path) {
		this.head_get_path = head_get_path;
	}

	public String getHead_home_upload_path() {
		return head_home_upload_path;
	}

	public void setHead_home_upload_path(String head_home_upload_path) {
		this.head_home_upload_path = head_home_upload_path;
	}

	public String getHead_home_get_path() {
		return head_home_get_path;
	}

	public void setHead_home_get_path(String head_home_get_path) {
		this.head_home_get_path = head_home_get_path;
	}

	public String getFile_tmp() {
		return file_tmp;
	}

	public void setFile_tmp(String file_tmp) {
		this.file_tmp = file_tmp;
	}

	private class Search {
		String key;
		int order;
		String type;
		Date date;
	}
}
