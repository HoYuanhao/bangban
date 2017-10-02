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

import com.xunyanhui.bean.Suggest;

import com.xunyanhui.model.User;

import com.xunyanhui.service.SuggestService;
import com.xunyanhui.service.UserService;

@Controller
@RequestMapping("/")
public class SuggestController {
	/** 日志实例 */
	private static final Logger logger = Logger
			.getLogger(SuggestController.class);
	@Resource
	SuggestService suggestService;

	/*
	 * 新建申诉，当演艺活动发布者对某一演艺活动的报名的工作提出申诉后，将停止演艺活动资金支付
	 * 待申诉解决后根据解决的情况进行资金的返还或是将资金支付给演艺人员
	 */
	@RequestMapping(value = "suggest/new", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String newComplain(HttpServletRequest request,
			@RequestParam("contact") String contact,
			@RequestParam("desc") String desc) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		Suggest suggest =  new Suggest();
		suggest.setContact(contact);
		suggest.setDescription(desc);
		if (user != null) {
			suggest.setUid(user.getId());
		} else {
			suggest.setUid("00000000");//表示匿名用户
		}
		int ret = suggestService.newSuggest(suggest);
		if(ret==1){
			result.put("msg", "建议反馈提交成功！");
			result.put("code", "1");
		}else{
			result.put("msg", "建议反馈提交失败！");
			result.put("code", "0");
		}
		return JSONObject.fromObject(result).toString();
	}
	/*
	 * 新建申诉，当演艺活动发布者对某一演艺活动的报名的工作提出申诉后，将停止演艺活动资金支付
	 * 待申诉解决后根据解决的情况进行资金的返还或是将资金支付给演艺人员
	 */
	@RequestMapping(value = "suggest/get/{artistid}/{performanceid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getComplain(HttpServletRequest request,
			@PathVariable("artistid") String artistid,
			@PathVariable("performanceid") String performanceid) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

}
