/**
 * 创建日期：2017-1-11下午4:06:09
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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import com.xunyanhui.model.Advice;

import com.xunyanhui.model.User;

import com.xunyanhui.service.AdviceService;

import com.xunyanhui.service.UserService;

@Controller
@RequestMapping("/")
public class AdviceController {
	/** 日志实例 */
	private static final Logger logger = Logger
			.getLogger(AdviceController.class);
	
	@Resource
	AdviceService adviceService;
	
	/*
	 * 新建反馈和建议
	 */
	@RequestMapping(value = "advice/newAdvice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getBalance(HttpServletRequest request,
			@RequestParam("content")String content) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			Advice advice = new Advice();
			advice.setContent(content);
			advice.setUid(user.getId());
			int ret = adviceService.newAdvice(advice);
			if(ret==1){
				result.put("msg", "新建建议与反馈成功！");
				result.put("code", "1");
			}
			else{
				result.put("msg", "新建建议与反馈失败！");
				result.put("code", "0");
			}		
		}
		else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
}
