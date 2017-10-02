package com.xunyanhui.controller;

//import com.citic.test.entity.Person;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基于Restful风格架构测试
 * 
 * @author xingchuanjun
 * @version V1.0
 * @history 2016-11-14 下午3:00:12 xingchuanjun 修改
 */
@Controller
@RequestMapping(value="/restful")
public class RestfulController {

	/** 日志实例 *//*
	private static final Logger logger = Logger.getLogger(RestfulController.class);

	@RequestMapping(value = "/hello", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String hello() {
		return "你好！hello";
	}
	*/

	@RequestMapping(value = "/say/{msg}", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String say(@PathVariable(value = "msg") String msg,
			@RequestParam(value = "bio") String bio) {
		return "{\"msg\":\"you say:'" + msg+" ;"+bio + "'\"}";
	}
	/*
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
