/**
 * 创建日期：2017-1-11下午4:06:09
 * 修改日期：
 * 作者：邢传军
 * 处理城市列表
 */
package com.xunyanhui.controller;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xunyanhui.model.User;
import com.xunyanhui.service.AccountService;
import com.xunyanhui.service.UserService;
import com.xunyanhui.service.impl.Sendsms;
@Controller
@RequestMapping("/")
public class UitlController {
	/** 日志实例 */
	private static final Logger logger = Logger.getLogger(UitlController.class);
	@Autowired
	private UserService userService;
	@Resource
	AccountService accountService;
	@Autowired
	private Sendsms sendsms;
	
	/*
	 * 支付过程中获取验证码
	 */
	@RequestMapping(value = "getCheckCode", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getCheckCode(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if (user != null) {
			String mobile = accountService.getMobile(user.getId());
			if(mobile==null||mobile.equals("")){
				result.put("msg", " 未绑定手机号！");
				result.put("code", "-2");
			}else{
				int checkcode = sendsms.getcode();
				session.setAttribute("code", checkcode);
				boolean fudge= sendsms.sendvc(checkcode, mobile);
				if(fudge){
					session.setAttribute("bill",checkcode );
					result.put("mobile", mobile.substring(0,3)+"****"+mobile.substring(7));
					result.put("msg", " 发送成功！");
					result.put("code", "1");
				}else{
					result.put("msg", " 获取验证码失败，稍后重试");
					result.put("code", "-3");
				}
			}
		}
		else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	
	/*
	 * 验证验证码是否有效
	 */
	@RequestMapping(value = "verifyCheckCode/{checkcode}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String verifyCheckCode(HttpServletRequest request,
			@PathVariable("checkcode") String checkcode) {
		HttpSession session = request.getSession();
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if (user != null) {
			String mobile = accountService.getMobile(user.getId());
			if(mobile==null||mobile.equals("")){
				result.put("msg", " 用户未绑定手机！");
				result.put("code", "-2");
			}else{
				
				int code=(int) request.getSession().getAttribute("code");
				if(String.valueOf(code).equals(checkcode)){
					session.setAttribute(AccountService.PAY_PASSWORD, "1");
					result.put("msg", " 验证成功！");
					result.put("code", "1");
				}else{
					result.put("msg", " 验证失败！");
					result.put("code", "-3");
				}
			}
		}
		else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	/*
	 * 用于测试部署的项目是否启动
	 */
	@RequestMapping(value = "test", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String test(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "success");
		return JSONObject.fromObject(result).toString();
	}
	/*
	 * 找回密码的是时候来获取验证码，需要用户自行提交手机号，并验证手机号是否注册过
	 * 如未注册，则先提示用户
	 * 如以注册，则发送验证码，并返回验证码发送成功
	 */
	@RequestMapping(value = "checkCode/{mobile}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getCheckCodeofForgatPassword(HttpServletRequest request,
			@PathVariable("mobile") String mobile) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean isRegist = userService.hasPhoneNum(mobile);
		if(isRegist){
			int code=sendsms.getcode();
			request.getSession().setAttribute("code", code);
			boolean send = sendsms.sendvc(code, mobile);
			if(send){
				result.put("msg", "验证码发送成功！");
				result.put("code", "1");
			}else{
				result.put("msg", "验证码发送失败！");
				result.put("code", "0");
			}
		}
		else {
			result.put("msg", "该移动号码尚未注册用户！");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
}
