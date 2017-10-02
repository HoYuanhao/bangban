package com.xunyanhui.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@RequestMapping(value="",produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String test1(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model){
		return "{\"msg\":\"error\"}";
	}
}
