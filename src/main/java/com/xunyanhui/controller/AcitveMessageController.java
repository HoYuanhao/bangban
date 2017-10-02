/**
 * 创建日期：2016-12-12下午13:29:02
 * 修改日期：
 * 作者：邢传军
 * 目的：实现系统活动消息处理
 */
package com.xunyanhui.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import com.xunyanhui.service.ActiveMessageService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xunyanhui.model.ActiveMessageList;

@Controller 
@RequestMapping(value="/activemsg")
public class AcitveMessageController {
	/** 日志实例 */
	private static final Logger logger = Logger.getLogger(AcitveMessageController.class);
	private @Value("#{test['REPOSITORY_PATH_AMSG']}")  String file_upload_path;
	private @Value("#{test['IMAGE_BASE_URL_AMSG']}")  String file_get_path;
	@Resource
	ActiveMessageService activeMessageService;

	
	/*
	 * (分页模式)读取系统中存储的活动消息列表
	 * token				用户认证token
	 * uid					当前用户id(即接收信息的用户id)
	 * page					页号
	 * size					页的大小
	 */
	@RequestMapping(value = "/getList/{token}/{uid}/{page}/{size}.json", method = RequestMethod.GET,produces ="application/json;charset=UTF-8")
	public @ResponseBody
	String getSingelMessageList(@PathVariable("token") String token,
			@PathVariable("uid") String uid,
			@PathVariable("page") int page,
			@PathVariable("size") int size) {
        System.out.println("sid"+uid);
       
		List<ActiveMessageList> list = activeMessageService.getActiveMessageList(page, size);	
		
		return JSONArray.fromObject(list).toString();
	}
	
	/*
	 * (分页模式)读取系统中存储的活动消息列表
	 * token				用户认证token
	 * uid					当前用户id(即接收信息的用户id)
	 * page					页号
	 * size					页的大小
	 */
	@RequestMapping(value = "/getListManage/{token}/{uid}/{page}/{size}.json", method = RequestMethod.GET,produces ="application/json;charset=UTF-8")
	public @ResponseBody
	String getSingelMessageListManage(@PathVariable("token") String token,
			@PathVariable("uid") String uid,
			@PathVariable("page") int page,
			@PathVariable("size") int size) {
        System.out.println("sid"+uid);
        int total;
        total = activeMessageService.getActiveMessageCount();
		List<ActiveMessageList> list = activeMessageService.getActiveMessageList(page, size);	
		Map<String, Object> map = new HashedMap();
		map.put("total", total);
		map.put("result", list);
		return JSONObject.fromObject(map).toString();
	}
	
	@RequestMapping(value="/newActiveMessge/new.json",method = RequestMethod.POST)
    public String  fileUpload2(HttpServletRequest request,
    		@RequestParam("uid") String uid,
    		@RequestParam("details") String details,
    		@RequestParam("topic") String topic,
    		@RequestParam("scope") String scope,
    		@RequestParam("file") CommonsMultipartFile file) throws IOException {
		
        String path =this.getFile_upload_path();
        int ret = 0;
        String fileName =  file.getOriginalFilename();
        if(fileName.contains(".")){
        	  //String fileName = file.getOriginalFilename();
        	fileName = new Date().getTime()+"."+this.getFileType(file.getOriginalFilename());
            path=path+"/"+fileName;
            File newFile=new File(path);
            //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
            file.transferTo(newFile);
            ret = activeMessageService.insertActiveMessage(uid, fileName, details, topic, scope,this.getFile_get_path());
        }
        String ret_s = "error";
        if(ret==1){
        	ret_s = "success";
        }
        return ret_s; 
    }
	@RequestMapping("/newFile/.json")
    public String  fileUpload3() throws IOException {
         System.out.println(this.getFile_upload_path());
        return "/success"; 
    }

	public String getFile_upload_path() {
		return file_upload_path;
	}

	public void setFile_upload_path(String file_upload_path) {
		this.file_upload_path = file_upload_path;
	}
	
	private String getFileType(String fileName){
		String type;
		String[] s = fileName.split("\\.");
		for(int i=0;i<s.length;i++){
			System.out.println(s[i]);
		}
		type = s[1];
		return type;
	}

	public String getFile_get_path() {
		return file_get_path;
	}

	public void setFile_get_path(String file_get_path) {
		this.file_get_path = file_get_path;
	}
	

}
