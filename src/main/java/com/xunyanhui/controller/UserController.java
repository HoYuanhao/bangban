package com.xunyanhui.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.comm.ClientContext;
import com.easemob.server.example.comm.EasemobRestAPIFactory;
import com.easemob.server.example.comm.body.IMUserBody;
import com.easemob.server.example.comm.wrapper.BodyWrapper;
import com.xunyanhui.bean.UserInfoSimple;
import com.xunyanhui.exception.DaoException;
import com.xunyanhui.model.Account;
import com.xunyanhui.model.User;
import com.xunyanhui.service.AccountService;
import com.xunyanhui.service.ArtistService;
import com.xunyanhui.service.MyInfoService;
import com.xunyanhui.service.UserService;
import com.xunyanhui.service.impl.Sendsms;
import com.xunyanhui.utils.DistanceUtil;
import com.xunyanhui.utils.RegUtil;
import com.xunyanhui.utils.UUidUtil;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private MyInfoService myInfoService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private Sendsms sendsms;
	@Autowired
	private ArtistService artistservice;
	private static Logger logger = Logger.getLogger(UserController.class);
	@RequestMapping(value = "/test", produces = "application/json;charset=UTF-8")
	public @ResponseBody String test1(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		double distance = DistanceUtil.GetDistance(123.345, 45.789, 123.346, 45.790);
		return "{\"msg\":\"" + distance + "\"}";
	}

	/*
	 * 登录
	 */
	@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
	public @ResponseBody String login(HttpServletRequest request, @RequestParam("userName") String userName,
			@RequestParam("password") String password, @RequestParam("lat") String lat,
			@RequestParam("lng") String lng) {
		String error = null;
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			error = "用户名错误";
		} catch (IncorrectCredentialsException e) {
			error = "密码错误";
		} catch (DaoException e) {
			error = "数据库操作错误";
		} catch (AuthenticationException e) {

			// 其他错误，比如锁定，如果想单独处理请单独catch处理
			error = "其他错误：" + e.getMessage();
		}
		logger.debug("是否有 user 权限 :" + subject.hasRole("user"));
		if (error != null) {
			return "{\"msg\":\"" + error + "\",\"code\":\"0\"}";
		} else {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(UserService.LOGIN_USER);
			UserInfoSimple userInfoSimple = myInfoService.getSimpleUserInfo(user.getId());
			artistservice.updateLatAndLngById(user.getId(), lat, lng);
			return "{\"msg\":\"登录成功\",\"code\":\"1\",\"userInfo\":" + JSONObject.fromObject(userInfoSimple).toString()
					+ "}";
		}
	}

	/*
	 * 登出
	 */
	@RequestMapping(value = "/logout", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		return "{\"msg\":\"success\",\"code\":\"1\"}";
	}

	@RequestMapping(value = "/sid", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getSid(HttpServletRequest request) {
		System.out.println("sid");
		request.getSession();
		return "{\"msg\":\"success\",\"code\":\"1\"}";
	}

	/*
	 * 注册接口
	 */
	/*
	 * ---------------------------------- | coce | 1 | 注册成功 |
	 * ---------------------------------- | coce | -1 | 用户名不符合规则 |
	 * ---------------------------------- | coce | -2 | 手机号不符合规则 |
	 * ---------------------------------- | coce | -3 | 密码不符合规则 |
	 * ---------------------------------- | coce | -4 | 手机号不符合规则 |
	 * ---------------------------------- | coce | -5 | 用户名不符合规则 |
	 * ---------------------------------- | coce | -6 | 手机验证码输入错误 |
	 * ----------------------------------
	 */
	@RequestMapping(value = "/regist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String regist(@RequestParam("mobile") String mobile, @RequestParam("code") String code,
			@RequestParam("password") String password, @RequestParam("userName") String userName,
			@RequestParam("cid") String cid, HttpSession session) {
		System.out.println("注册  mobile:" + mobile + "userName:" + userName + "dsfd s  " + code);
		String result = null;
		int vcode = (int) session.getAttribute("code");
		if (!RegUtil.regUserName(userName)) {
			result = "{\"code\":-1}";
		} else if (!RegUtil.regMobile(mobile)) {
			result = "{\"code\":-2}";
		} else if (!RegUtil.regPasswd(password)) {
			result = "{\"code\":-3}";
		} else if (userService.hasPhoneNum(mobile)) {
			result = "{\"code\":-4}";
		} else if (userService.hasUserName(userName)) {
			result = "{\"code\":-5}";
		} else if (!(String.valueOf(vcode).equals(code))) {
			result = "{\"code\":-6}";
		} else {
			User user = new User();
			user.setUserName(userName);
			user.setPassword(password);
			user.setMobile(mobile);
			user.setRegisterTime(new Date());
			user.setCid(cid);
			user.setId(UUidUtil.getUUid());
			System.out.println("注册中");
			if (userService.regist(user)) {
				Account account = new Account();
				account.setPassword("123456");
				account.setUserName(user.getUserName());
				account.setMobile(user.getMobile());
				account.setUid(user.getId());
				account.setAccountId(UUidUtil.getUUid());
				int create_ret = accountService.newAccount(account);
				if (create_ret == 0) {
					result = "{\"code\":0}";
				} else {
					/*
					 * 添加用户注册环信用户功能，暂时这样，待与前端约定后进一步处理
					 * 
					 */
					EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES)
							.getAPIFactory();
					IMUserAPI user1 = (IMUserAPI) factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
					BodyWrapper userBody = new IMUserBody(user.getId(), user.getId(), user.getUserName());
					user1.createNewIMUserSingle(userBody);
					result = "{\"code\":1}";
				}
			}
		}
		return result;

		/*
		 * if (!userService.hasPhoneNum(mobile) &&
		 * !userService.hasUserName(userName) )
		 */
	}

	/**
	 * 获取验证码
	 * 
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/verifyMobile/{mobile}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String verifyMobile(@PathVariable("mobile") String mobile) {
		String result = null;
		System.out.println("verifyMobile" + mobile);
		if (userService.hasPhoneNum(mobile)) {
			result = "{\"code\":0}";
		} else {
			result = "{\"code\":1}";
		}
		return result;
	}

	@RequestMapping(value = "/verifyUserName/{userName}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String verifyUserName(@PathVariable("userName") String userName) {
		String result = null;
		System.out.println("verifyUserName" + userName);
		if (userService.hasUserName(userName)) {
			result = "{\"code\":0}";
		} else {
			result = "{\"code\":1}";
		}
		return result;
	}

	@RequestMapping(value = "/sendSMS/{mobile}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String sendSMS(@PathVariable("mobile") String mobile, HttpServletRequest request) {
		System.out.println("sendSMS" + mobile);
		int code = sendsms.getcode();
		if (String.valueOf(code) != null) {
			HttpSession session = request.getSession();
			session.setAttribute("code", code);
			sendsms.sendvc(code, mobile);
			return "{\"code\":1}";
		} else {
			return "{\"code\":0}";
		}
	}
	/*
	 * 忘记密码用注册手机号+验证码+新密码，进行密码维护
	 */

	@RequestMapping(value = "/forgetPassword/{mobile}/{checkcode}/{newpwd}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String forgetPassword(@PathVariable("mobile") String mobile, @PathVariable("checkcode") String checkcode,
			@PathVariable("newpwd") String newpwd, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean isRegist = userService.hasPhoneNum(mobile);
		if (isRegist) {
			HttpSession session = request.getSession();
			int code = (int) session.getAttribute("code");
			if (String.valueOf(code).equals(checkcode)) {
				int count = userService.updatePasswdByMobile(mobile, newpwd);
				if (count >= 1) {
					result.put("msg", "找回密码成功！");
					result.put("code", "1");
				} else {
					result.put("msg", "找回密码失败！");
					result.put("code", "0");
				}

			} else {
				result.put("msg", "验证码不正确！");
				result.put("code", "-2");
			}
		} else {
			result.put("msg", "该移动号码尚未注册用户！");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	/*
	 * 忘记密码用注册手机号+验证码+新密码，进行密码维护
	 */

	@RequestMapping(value = "/changePWD/{oldPWD}/{newPWD}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String forgetPassword(@PathVariable("oldPWD") String oldPWD, @PathVariable("newPWD") String newPWD,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			String id = user.getId();
			int ret = userService.updatePasswd(id, oldPWD, newPWD);
			if (ret == 0) {
				result.put("msg", "原密码错误，密码更新失败！");
				result.put("code", "0");
			} else {
				result.put("msg", "密码成功！");
				result.put("code", "1");
			}
		} else {
			result.put("msg", "用户未登录！");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
}
