/**
 * 创建日期：2016-12-16下午2:31:57
 * 修改日期：
 * 作者：邢传军
 * 目的：用于进行系统消息的管理和操作，发布，查询遍历等
 */
package com.xunyanhui.controller;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import com.xunyanhui.model.SystemMessageList;
import com.xunyanhui.model.User;
import com.xunyanhui.service.SystemMessageService;
import com.xunyanhui.service.UserService;

@Controller 
@RequestMapping(value="/systemmsg")
public class SystemMessageController {
	/** 日志实例 */
	private static final Logger logger = Logger.getLogger(SystemMessageController.class);
	@Resource
	SystemMessageService systemMessageService;
	/*
	 * (分页模式)读取系统中存储的活动消息列表,带时间戳的
	 * token				用户认证token
	 * uid					当前用户id(即接收信息的用户id)
	 * date					时间戳，查询指定时间之前的
	 * page					页号
	 * size					页的大小
	 */
	@RequestMapping(value = "/getList/{date}/{page}/{size}.json", method = RequestMethod.GET,produces ="application/json;charset=UTF-8")
	public @ResponseBody
	String getSystemMessageList(HttpServletRequest request,
			@PathVariable("date") String date,
			@PathVariable("page") int page,
			@PathVariable("size") int size) {
        System.out.println("date"+date);
        Map<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if (user != null) {
			Long lt = new Long(date);
			List<SystemMessageList> list = systemMessageService.getSystemMessageList(user.getId(),lt,page,size);
			result.put("result", list);
			result.put("msg", "访问成功");
			result.put("code", "1");
		}
		else{
			result.put("msg", "用户未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	
}
