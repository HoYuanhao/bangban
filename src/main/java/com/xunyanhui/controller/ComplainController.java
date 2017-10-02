/**
 * 创建日期：2017-3-3上午10:06:09
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.controller;

import java.util.HashMap;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunyanhui.model.Complain;
import com.xunyanhui.model.EntryRecordN;
import com.xunyanhui.model.User;
import com.xunyanhui.service.ComplainService;
import com.xunyanhui.service.MyPerformanceService;
import com.xunyanhui.service.UserService;

@Controller
@RequestMapping("/")
public class ComplainController {
	/** 日志实例 */
	private static final Logger logger = Logger
			.getLogger(ComplainController.class);

	@Resource
	ComplainService complainService;
	@Resource
	MyPerformanceService myPerformanceService;

	/*
	 * 新建申诉，当演艺活动发布者对某一演艺活动的报名的工作提出申诉后，将停止演艺活动资金支付
	 * 待申诉解决后根据解决的情况进行资金的返还或是将资金支付给演艺人员
	 */
	@RequestMapping(value = "complain/new/{artistid}/{performanceid}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String newComplain(HttpServletRequest request,
			@PathVariable("artistid") String artistid,
			@PathVariable("performanceid") String performanceid,
			@RequestParam("desc") String desc) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			String uid = user.getId();
			/*
			 * 检查用户是否发布过演艺同时艺人是否报名
			 */
			EntryRecordN entryRecord = myPerformanceService.getEntryRecord(
					artistid, performanceid);
			if (entryRecord == null) {
				result.put("msg", "报名的演艺活动不存在");
				result.put("code", "-2");
			} else {
				// 只用state的值为2时才允许进行投诉活动，并将其state值设置为5，这是后台的自动化程序将终止对支付状态的自动处理
				if (entryRecord.getState() == 2) {
					/*
					 * 创建投诉活动并将报名状态修改为5
					 */
					Complain complain = new Complain();
					complain.setArtistid(artistid);
					complain.setDescription(desc);
					complain.setPerformanceid(performanceid);
					complain.setUid(uid);
					complain.setState(1);
					if (complainService.newComplain(complain) == 1) {
						result.put("msg", "申诉提交成功！");
						result.put("code", "1");
					} else {
						result.put("msg", "申诉提交失败！");
						result.put("code", "0");
					}
				} else {
					result.put("msg", "当前状态为不可申诉状态");
					result.put("code", "-3");
				}
			}
		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 新建申诉，当演艺活动发布者对某一演艺活动的报名的工作提出申诉后，将停止演艺活动资金支付
	 * 待申诉解决后根据解决的情况进行资金的返还或是将资金支付给演艺人员
	 */
	@RequestMapping(value = "complain/get/{artistid}/{performanceid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getComplain(HttpServletRequest request,
			@PathVariable("artistid") String artistid,
			@PathVariable("performanceid") String performanceid) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			/*
			 * 检查用户是否发布过演艺同时艺人是否报名
			 */
			EntryRecordN entryRecord = myPerformanceService.getEntryRecord(
					artistid, performanceid);
			if (entryRecord == null) {
				result.put("msg", "报名的演艺活动不存在");
				result.put("code", "-2");
			} else {
				Complain complain = complainService.getComplain(artistid,
						performanceid);
				if (complain != null) {
					result.put("complain", complain);
					result.put("msg", "申诉信息读取成功！");
					result.put("code", "1");
				} else {
					result.put("msg", "申诉信息不存在！");
					result.put("code", "0");
				}
			}
		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

}
