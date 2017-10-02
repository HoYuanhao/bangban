package com.xunyanhui.controller;

//import com.citic.test.entity.Person;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.comm.ClientContext;
import com.easemob.server.example.comm.EasemobRestAPIFactory;
import com.easemob.server.example.comm.body.IMUserBody;
import com.easemob.server.example.comm.wrapper.BodyWrapper;
import com.easemob.server.example.comm.wrapper.ResponseWrapper;
import com.xunyanhui.bean.UserInfoForEasemob;
import com.xunyanhui.model.User;
import com.xunyanhui.service.UserService;
/**
 * 基于Restful风格架构的环信集成
 * 
 * @author xingchuanjun
 * @version V1.0
 * @history 2017-02-14 
 */
@Controller
@RequestMapping(value="/emchat")
public class EmChatController {
	/** 日志实例 */
	private static final Logger logger = Logger.getLogger(EmChatController.class);
	@Autowired
	private UserService userService;
	/*
	 * 
	 */
	@RequestMapping(value = "/resgistUser/{uid}/{password}/{nickname}",method = RequestMethod.GET , produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String resgistUser(HttpServletRequest request,
			@PathVariable("uid") String uid,
			@PathVariable("password") String password,
			@PathVariable("nickname") String nickname) {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		User user1 = (User) session.getAttribute(UserService.LOGIN_USER);
		if (user1 != null) {
		EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		IMUserAPI user = (IMUserAPI)factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
		BodyWrapper userBody = new IMUserBody(uid, password, nickname);
		ResponseWrapper fileResponse = (ResponseWrapper)user.createNewIMUserSingle(userBody);
		switch(fileResponse.getResponseStatus()){
			case 200:
				result.put("code","1");
				result.put("msg","用户注册成功");
				break;
			default:
				result.put("code","0");
				result.put("msg","用户注册失败");
				
		}
		}
		else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	@RequestMapping(value = "/deleteUser/{userName}", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String deleteUser(@PathVariable("userName") String userName) {
		Map<String, Object> result = new HashMap<String, Object>();
		EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();

		IMUserAPI user = (IMUserAPI)factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
		Object ret = user.deleteIMUserByUserName("User103");
		result.put("ret",ret.toString() );
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value = "/addUsers", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String addUsers() {
		Map<String, Object> result = new HashMap<String, Object>();
		EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		List<UserInfoForEasemob> userList = userService.selectAllUser();
		IMUserAPI user = (IMUserAPI)factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
		Object ret = null;
		for(int i =0;i<userList.size();i++){
			BodyWrapper userBody = new IMUserBody(userList.get(i).getId(), userList.get(i).getPassword(), userList.get(i).getNickName());
			ret = user.createNewIMUserSingle(userBody);
		}
		
		result.put("ret",ret.toString() );
		return JSONObject.fromObject(result).toString();
	}
	
	
	/*
	@RequestMapping(value = "/say/{msg}", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String say(@PathVariable(value = "msg") String msg) {
		return "{\"msg\":\"you say:'" + msg + "'\"}";
	}

	@RequestMapping(value = "/person/{id:\\d+}", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getPerson(@PathVariable("id") int id) {
		logger.info("获取人员信息id=" + id);
		System.out.println("用户提交"+id);
		Add person = new Add();
		person.setId("张三");
		person.setTname("男");
		person.setTpwd("30");
		return JSONObject.fromObject(person).toString();
	}

	@RequestMapping(value = "/person/{id:\\d+}", method = RequestMethod.DELETE)
	public @ResponseBody
	Object deletePerson(@PathVariable("id") int id) {
		logger.info("删除人员信息id=" + id);
		System.out.println("delete id is "+id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "删除人员信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "/person", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody
	Object addPerson(Add person) {
		logger.info("注册人员信息成功id=" + person.getId());
		System.out.println("用户提交Post"+person.getId());
		System.out.println("用户提交Post"+person.getTname());
		System.out.println("用户提交Post"+person.getTpwd());	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "注册人员信息成功");
		return jsonObject.toString();
	}

	@RequestMapping(value = "/person", method = RequestMethod.PUT)
	public @ResponseBody
	Object updatePerson(Add person) {
		logger.info("更新人员信息id=" + person.getId());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "更新人员信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "/searchPerson", method = RequestMethod.POST)
	public @ResponseBody
	List<Add> listPerson(@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		logger.info("查询人员name like " + name);
		List<Add> lstPersons = new ArrayList<Add>();

		Add person = new Add();
		person.setId("张三");
		person.setTname("男");
		person.setTpwd("30");
		lstPersons.add(person);

		Add person2 = new Add();
		person.setId("张四");
		person.setTname("男");
		person.setTpwd("30");
		lstPersons.add(person2);

		Add person3 = new Add();
		person.setId("张五");
		person.setTname("男");
		person.setTpwd("30");
		lstPersons.add(person3);

		return lstPersons;
	}*/

}
