/**
 * 创建日期：2016-12-6下午5:29:02
 * 修改日期：
 * 主要是先系统的私聊消息处理
 * 作者：邢传军
 */
package com.xunyanhui.controller;

import java.io.IOException;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.xunyanhui.service.MyInfoService;
import com.xunyanhui.service.SystemPrivateMessageService;
import com.xunyanhui.service.UserService;



import org.apache.activemq.command.ActiveMQQueue;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.comm.ClientContext;
import com.easemob.server.example.comm.EasemobRestAPIFactory;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.IQueryResult;
import com.gexin.rp.sdk.base.impl.AppMessage;

import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.xunyanhui.bean.MuiMessage;
import com.xunyanhui.bean.UserInfoSimple;

import com.xunyanhui.jms.bean.MessageInfo;
import com.xunyanhui.jms.service.JMSProducer;
import com.xunyanhui.model.SystemPrivateMessageList;
import com.xunyanhui.model.User;

@Controller
@RequestMapping(value="/message")
public class MessageController {
	/** 日志实例 */
	private static final Logger logger = Logger.getLogger(MessageController.class);
	private @Value("#{getui['appId']}")  String appId;
	private @Value("#{getui['appKey']}")  String appKey;
	private @Value("#{getui['masterSecret']}")  String masterSecret;
	private @Value("#{getui['appSecret']}")  String appSecret;
	
	private static final String host = "http://sdk.open.api.igexin.com/apiex.htm";
	@Resource
	SystemPrivateMessageService systemPrivateMessage;
	@Autowired
	private MyInfoService myInfoService;
	// 用于jms通讯的实体属性
	@Resource 
	private JMSProducer jMSProducer;
	@Resource 
	private ActiveMQQueue queueDestination;

	/*
	 * (分页模式)读取指定用户接收到的消息列表
	 * token				用户认证token
	 * uid					当前用户id(即接收信息的用户id)
	 * page					页号
	 * size					页的大小
	 */
	@RequestMapping(value = "/getList/{token}/{uid}/{page}/{size}.json", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getSingelMessageList(HttpServletRequest request,
			@PathVariable("token") String token,
			@PathVariable("uid") String uid,
			@PathVariable("page") int page,
			@PathVariable("size") int size) {
        System.out.println("sid"+uid);
        HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> retMap = new HashMap<String, Object>();
		if (user != null) {
			//total = systemPrivateMessage.getSingelMessageCount(sid);
			List<SystemPrivateMessageList> list = systemPrivateMessage.getSingleMessageList(uid, page, size);	
			//retMap.put("total", total);
			retMap.put("result", list);
			retMap.put("msg", "访问成功");
			retMap.put("code", "1");
		}
		else {
			retMap.put("msg", "未登录");
			retMap.put("code", "-1");
		}
		return JSONObject.fromObject(retMap).toString();
	}
	/*
	 * (分页模式)读取指定用户接收到某一用户的消息列表
	 * token				用户认证token
	 * uid					当前用户id(即接收信息的用户id)
	 * sid					发送信息的用户id
	 * page					页号
	 * size					页的大小
	 */
	@RequestMapping(value = "/getListBySender/{token}/{uid}/{sid}/{page}/{size}.json", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getSingelMessageListBySender(@PathVariable("token") String token,
			@PathVariable("uid") String uid,
			@PathVariable("sid") String sid,
			@PathVariable("page") int page,
			@PathVariable("size") int size) {
        System.out.println("sid"+sid+" "+page+" "+size);
		List<SystemPrivateMessageList> list = systemPrivateMessage.getSingleMessageListBySender(uid, sid, page, size);	
		return JSONArray.fromObject(list).toString();
	}
	
	/*
	 * (分页模式)读取指定用户接收到某一用户的消息列表,为适应mui请求数据的返回格式
	 * token				用户认证token
	 * uid					当前用户id(即接收信息的用户id)
	 * sid					发送信息的用户id
	 * page					页号
	 * size					页的大小
	 */
	@RequestMapping(value = "/getListBySenderMui/{token}/{uid}/{sid}/{page}/{size}.json", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getSingelMessageListBySenderNew(HttpServletRequest request,
			@PathVariable("token") String token,
			@PathVariable("uid") String uid,
			@PathVariable("sid") String sid,
			@PathVariable("page") int page,
			@PathVariable("size") int size) {
        System.out.println("sid"+sid+" "+page+" "+size);
        HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		if (user != null) {
			UserInfoSimple userInfoSimple = myInfoService
					.getSimpleUserInfo(user.getId());
			Integer messageCount = systemPrivateMessage.conutSingleMessageListBySender(uid, sid);
			if(messageCount>size){
				page = messageCount-size;
			}
			
			List<SystemPrivateMessageList> list = systemPrivateMessage.getSingleMessageListBySender(uid, sid, page, size);	
			List<MuiMessage> list1 = new ArrayList<MuiMessage>();
			for(int i = 0; i < list.size(); i++)  
			{  
				MuiMessage msg = new MuiMessage();
				msg.setContent(list.get(i).getDetails());
				msg.setTime(list.get(i).getReleaseTime());
				msg.setType("text");
				if(list.get(i).getReleaseId().equals(uid))
					msg.setSender("self");
				else
					msg.setSender(list.get(i).getReleaseId());
				list1.add(msg);
            //System.out.println(list.get(i));  
			}  
			
			retMap.put("result", list1);
			retMap.put("pic", userInfoSimple.getPic());
			retMap.put("msg", "成功");
			retMap.put("code", "1");
		}
		else {
			retMap.put("msg", "未登录");
			retMap.put("code", "-1");
		}
		System.out.println(JSONObject.fromObject(retMap).toString());
		return JSONObject.fromObject(retMap).toString();
	}
	/*
	 * 发送咨询私信，发送给指定的用户，通过用户名获取用户的在个推中的clientId
	 * 董瑞手机的：bcd302ee20c326d7702ec16cc952a059
	 * 我的手机的：2de1ac899b45d625164d6c0577d065d6
	 */
	@RequestMapping(value = "/send/{sid}/{uid}/{uname}.json", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String sendMessageSingle(HttpServletRequest request,
			@PathVariable("sid") String sid,
			@PathVariable("uid") String uid,
			@PathVariable("uname") String uname,
			@RequestParam("msg") String msg) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, String> retMap = new HashMap<String, String>();
		if (user != null) {
			if(sid.equals(user.getId())){
				if(systemPrivateMessage.insertSingleMessage(sid, uid, msg)==1){
					// 发送关注类型消息到jms系统
					MessageInfo msg1 = new MessageInfo();
					msg1.setType(1);
					msg1.setSid(sid);// 关注用户id
					msg1.setDid(uid);// 被关注艺人id
					msg1.setValue(msg);
					msg1.setOption(uname);//发送人昵称
					//jMSProducer.sendMessage(queueDestination, JSONObject.fromObject(msg1).toString();
					retMap.put("msg", "发送消息成功");
					retMap.put("code", "1");
				}
				else{
					retMap.put("msg", "发送消息失败");
					retMap.put("code", "0");
				}
			}
			else{
				retMap.put("msg", "用户异常！");
				retMap.put("code", "-1");
			}
		}
		else {
			retMap.put("msg", "未登录");
			retMap.put("code", "-1");
		}
		System.out.println(JSONObject.fromObject(retMap).toString());
		return JSONObject.fromObject(retMap).toString();
	}
	/*
	 * 用个推的通知消息来唤醒用户，特别是用户已经离线的情况或退出后台的情况下
	 */
	@RequestMapping(value = "/sendNotify/{sid}/{uid}/{uname}.json", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String sendNotify(HttpServletRequest request,
			@PathVariable("sid") String sid,
			@PathVariable("uid") String uid,
			@PathVariable("uname") String uname) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, String> retMap = new HashMap<String, String>();
		if (user != null) {
			if(sid.equals(user.getId())){
				Integer isFriend = systemPrivateMessage.conutSingleMessageListBySender(uid, sid);
				if(isFriend==0||isFriend==null){
					//将两个人设置为好友
					EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
					IMUserAPI userImApi = (IMUserAPI)factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
					Object ret = userImApi.addFriendSingle(sid, uid);
					System.out.println(ret.toString());
				}
				// 发送关注类型消息到jms系统
				MessageInfo msg1 = new MessageInfo();
				msg1.setType(1);
				msg1.setSid(sid);// 关注用户id
				msg1.setDid(uid);// 被关注艺人id
				msg1.setOption(uname);//发送人昵称
				jMSProducer.sendMessage(queueDestination, JSONObject.fromObject(msg1).toString());
				System.out.println("你发送了消息");
				retMap.put("msg", "发送消息成功");
				retMap.put("code", "1");
			}
			else{
				retMap.put("msg", "用户异常！");
				retMap.put("code", "-1");
			}
		}
		else {
			retMap.put("msg", "未登录");
			retMap.put("code", "-1");
		}
		System.out.println(JSONObject.fromObject(retMap).toString());
		return JSONObject.fromObject(retMap).toString();
	}
		/*ReturnString ret = new ReturnString();
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        if(systemPrivateMessage.insertSingleMessage(sid, uid, msg)==1){
        	
        	try {
				push.connect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			IQueryResult abc = push.getClientIdStatus(appId,cid);
			if (abc.getResponse().get("result").equals("Offline")) {
				user_status = 1;
			} else {
				user_status = 0;
			}
			// 透传消息模板，根据用户状态发送标准和非标的模板信息
			TransmissionTemplate template = getUserTemplate(user_status, uname,
					msg, uid);
			SingleMessage message = new SingleMessage();
			message.setOffline(true);
			// 离线有效时间，单位为毫秒，可选
			message.setOfflineExpireTime(24 * 3600 * 1000);
			message.setData(template);
			// 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
			message.setPushNetWorkType(0);
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(cid);
			// target.setAlias(Alias);
			IPushResult ret_getui = null;
			try {
				ret_getui = push.pushMessageToSingle(message, target);
			} catch (RequestException e) {
				e.printStackTrace();
				ret_getui = push.pushMessageToSingle(message, target,
						e.getRequestId());
			}
			if (ret_getui != null) {
				ret.setResult("success");
			} else {
				ret.setResult("failure");
			}
        }
        else{
        	ret.setResult("failure");
        }
		return JSONObject.fromObject(ret).toString();
	}*/
	/*
	 * 发送系统消息给系统消息给指定客户
	 * 董瑞手机的：bcd302ee20c326d7702ec16cc952a059
	 * 我的手机的：2de1ac899b45d625164d6c0577d065d6
	 */
	@RequestMapping(value = "/sendSystem.json", method = RequestMethod.GET,produces = "application/text;charset=UTF-8")
	public @ResponseBody
	String sendMessageSystem() {
		int user_status = 0;//标示用户的在线状态，1表示离线，0表示在线
		String s_ret = "success";
		System.out.println("success");
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        try {
			push.connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        IQueryResult abc = push.getClientIdStatus(appId, "2de1ac899b45d625164d6c0577d065d6");
        System.out.println(abc.getResponse().get("result"));
        if(abc.getResponse().get("result").equals("Offline")){
        	user_status = 1;
        }else{
        	user_status = 0;
        }
        System.out.println("user_status"+user_status);
        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        TransmissionTemplate template =null;
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);
        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);

        IPushResult ret = push.pushMessageToApp(message);
        s_ret = ret.getResponse().toString();
		return s_ret;
	}
	/*
	 * 发送活动消息给指定标签的用户
	 * 董瑞手机的：bcd302ee20c326d7702ec16cc952a059
	 * 我的手机的：2de1ac899b45d625164d6c0577d065d6
	 */
	@RequestMapping(value = "/sendActiveMsg.json", method = RequestMethod.GET,produces = "application/text;charset=UTF-8")
	public @ResponseBody
	String sendActiveMessage() {
		int user_status = 0;//标示用户的在线状态，1表示离线，0表示在线
		String s_ret = "success";
		System.out.println("success");
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        try {
			push.connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        IQueryResult abc = push.getClientIdStatus(appId, "2de1ac899b45d625164d6c0577d065d6");
        System.out.println(abc.getResponse().get("result"));
        if(abc.getResponse().get("result").equals("Offline")){
        	user_status = 1;
        }else{
        	user_status = 0;
        }
        System.out.println("user_status"+user_status);
        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        TransmissionTemplate template = null;
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);
        // 定义"AppMessage"类型消息对象，设置消息内容模板、发送的目标App列表、是否支持离线发送、以及离线消息有效期(单位毫秒)
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);

        IPushResult ret = push.pushMessageToApp(message);
        s_ret = ret.getResponse().toString();
		return s_ret;
	}
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getMasterSecret() {
		return masterSecret;
	}
	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	

}
