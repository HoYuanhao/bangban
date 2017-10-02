/**
 * 创建日期：2017-1-2下午5:34:15
 * 修改日期：
 * 作者：邢传军
 * 目的：用于艺人信息完善和管理
 */
package com.xunyanhui.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.util.
//import org.springframework.util.B

import com.xunyanhui.utils.Base64Coder;

import com.xunyanhui.bean.ArtistAttenListV;
import com.xunyanhui.bean.ArtistSelfInfo;
import com.xunyanhui.bean.ArtistSelfMoreInfo;
import com.xunyanhui.bean.AuthState;
import com.xunyanhui.bean.Base64String;


import com.xunyanhui.model.User;

import com.xunyanhui.service.MyInfoService;
import com.xunyanhui.service.MyPerformanceService;
import com.xunyanhui.service.UserService;


@Controller
@RequestMapping("/")
public class MyInfoController {
	/** 日志实例 */
	private static final Logger logger = Logger.getLogger(MyInfoController.class);
	
	//获取配置中的艺人头像(列表)文件上传路径和网络访问位置
	private @Value("#{test['REPOSITORY_PATH_HEAD']}")  String head_upload_path;
	private @Value("#{test['IMAGE_BASE_URL_HEAD']}")  String head_get_path;
	//获取配置中的艺人头像(列表)文件上传路径和网络访问位置
	private @Value("#{test['REPOSITORY_PATH_HEAD_HOME']}")  String head_home_upload_path;
	private @Value("#{test['IMAGE_BASE_URL_HEAD_HOME']}")  String head_home_get_path;
	
	//获取配置中的文件的临时中转路径
	private @Value("#{test['REPOSITORY_PATH_TEMP']}")  String file_tmp;
	@Resource
	MyInfoService myInfoService;
	@Resource
	MyPerformanceService myPerformanceService;
	
	/**
	 * 用于艺人维护其头像使用
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 * 
	 */
	@RequestMapping(value = "selfInfo/headPortrait", method = RequestMethod.POST,produces ="application/json;charset=UTF-8")
	public @ResponseBody String headPortrait(HttpServletRequest request,
			@RequestBody Base64String base64String) throws UnsupportedEncodingException {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if (user != null) {
			logger.info(user.getId()+"维护头像信息！");
			String dataPrix = "";
	        String data = "";
			String base64decode = base64String.getPath();
			if(base64decode == null || "".equals(base64decode)){
				result.put("msg", "上传失败，上传图片数据为空");
				result.put("code", "-2");
				
            }else{
                String [] d = base64decode.split("base64,");
                if(d != null && d.length == 2){
                    dataPrix = d[0];
                    data = d[1];
                }else{
                	result.put("msg", "上传失败，数据不合法");
    				result.put("code", "-3");
                }
            }
			String suffix = "";
            if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            }else{
            	result.put("msg", "上传图片格式不合法");
				result.put("code", "-4");
            }
            String tempFileName = user.getId() + suffix;
            try{
				byte[] ph = Base64Coder.decode(data);
				System.out.println(ph);
				for (int i = 0; i < ph.length; ++i) {
					if (ph[i] < 0) {// 调整异常数据
						ph[i] += 256;
					}
				}
				// 保存图片
				String imgFilePath = this.getHead_upload_path() + File.separator
						+ tempFileName;
				String imgFilePath_home = this.getHead_home_upload_path()
						+ File.separator + tempFileName;

				// 更新成功
				FileOutputStream out = new FileOutputStream(imgFilePath);
				FileOutputStream out1 = new FileOutputStream(imgFilePath_home);
				out.write(ph);
				out.flush();
				out.close();
				out1.write(ph);
				out1.flush();
				out1.close();
				int ret = myInfoService.updatePic(suffix, user.getId());
				logger.info("ret"+ret);
            }catch(Exception ee){
            	result.put("msg", "上传失败，写入文件失败");
    			result.put("code", "-5");
            }
            result.put("msg", "上传成功");
			result.put("code", "1");
			result.put("pic", suffix);
		}
		else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
        return JSONObject.fromObject(result).toString();
    }
	
	@RequestMapping(value = "selfInfo/start", method = RequestMethod.GET,produces ="application/json;charset=UTF-8")
	public @ResponseBody String start(HttpServletRequest request,
			@RequestParam(value = "uid", required = false) String uid) throws UnsupportedEncodingException {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if(user!=null){
			uid = user.getId(); 
			int isArtist = myPerformanceService.isArtist(uid);
			if(isArtist==0){
				isArtist = myInfoService.newArtist(uid,user.getUserName());
			}
			ArtistSelfInfo artistInfo = myInfoService.getArtistById(uid);
			if(artistInfo.getSex().equals("1")){
				artistInfo.setSex("男");
			}
			else{
				artistInfo.setSex("女");
			}
			result.put("artistInfo", artistInfo);	
			result.put("msg", "开始编辑用户信息");
			result.put("code", "1");
		}else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		
		return JSONObject.fromObject(result).toString();
	}
	@RequestMapping(value = "selfInfo/updateStageName", method = RequestMethod.POST,produces ="application/json;charset=UTF-8")
	public @ResponseBody String updateStageName(HttpServletRequest request,
			@RequestParam(value = "uid", required = false) String uid,
			@RequestParam(value = "stageName") String stageName) throws UnsupportedEncodingException {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if(user!=null){
			uid = user.getId();
			logger.info(""); 
			int isArtist = myPerformanceService.isArtist(uid);
			if(isArtist==0){
				isArtist = myInfoService.newArtist(uid,user.getUserName());
			}
			int isSuccess = myInfoService.updateStageName(stageName, uid);
			if(isSuccess==1){
				result.put("msg", "保存艺名成功");
				result.put("code", "1");
			}else{
				result.put("msg", "保存艺名失败");
				result.put("code", "0");
			}
			
		}else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		
		return JSONObject.fromObject(result).toString();
	}
	@RequestMapping(value = "selfInfo/updatePerfor", method = RequestMethod.POST,produces ="application/json;charset=UTF-8")
	public @ResponseBody String updatePerfor(HttpServletRequest request,
			@RequestParam(value = "uid", required = false) String uid,
			@RequestParam(value = "performancelist") String performancelist) throws UnsupportedEncodingException {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if(user!=null){
			uid = user.getId();
			logger.info(""); 
			int isArtist = myPerformanceService.isArtist(uid);
			if(isArtist==0){
				isArtist = myInfoService.newArtist(uid,user.getUserName());
			}
			int isSuccess = myInfoService.updatePerfor(performancelist, uid);
			if(isSuccess==1){
				result.put("msg", "保存演艺经历成功");
				result.put("code", "1");
			}else{
				result.put("msg", "保存演艺经历失败");
				result.put("code", "0");
			}
			
		}else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	@RequestMapping(value = "selfInfo/updateBioList", method = RequestMethod.POST,produces ="application/json;charset=UTF-8")
	public @ResponseBody String updateBiographyList(HttpServletRequest request,
			@RequestParam(value = "uid", required = false) String uid,
			@RequestParam(value = "biographylist") String biographylist) throws UnsupportedEncodingException {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if(user!=null){
			uid = user.getId();
			logger.info(""); 
			int isArtist = myPerformanceService.isArtist(uid);
			if(isArtist==0){
				isArtist = myInfoService.newArtist(uid,user.getUserName());
			}
			int isSuccess = myInfoService.updateBiographyList(biographylist, uid);
			if(isSuccess==1){
				result.put("msg", "保存获奖情况成功");
				result.put("code", "1");
			}else{
				result.put("msg", "保存获奖情况失败");
				result.put("code", "0");
			}
			
		}else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value = "selfInfo/updateBioHighest", method = RequestMethod.POST,produces ="application/json;charset=UTF-8")
	public @ResponseBody String updateBiographyHighest(HttpServletRequest request,
			@RequestParam(value = "uid", required = false) String uid,
			@RequestParam(value = "biohighest") String biographyhighest) throws UnsupportedEncodingException {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if(user!=null){
			uid = user.getId();
			logger.info(""); 
			int isArtist = myPerformanceService.isArtist(uid);
			if(isArtist==0){
				isArtist = myInfoService.newArtist(uid,user.getUserName());
			}
			int isSuccess = myInfoService.updateBiographyHighest(biographyhighest, uid);
			if(isSuccess==1){
				result.put("msg", "保存最高奖项成功");
				result.put("code", "1");
			}else{
				result.put("msg", "保存最高奖项失败");
				result.put("code", "0");
			}
			
		}else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value = "selfInfo/updateSelfIntro", method = RequestMethod.POST,produces ="application/json;charset=UTF-8")
	public @ResponseBody String updateSelfIntro(HttpServletRequest request,
			@RequestParam(value = "uid", required = false) String uid,
			@RequestParam(value = "selfintro") String selfintroduction) throws UnsupportedEncodingException {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if(user!=null){
			uid = user.getId();
			logger.info(""); 
			int isArtist = myPerformanceService.isArtist(uid);
			if(isArtist==0){
				isArtist = myInfoService.newArtist(uid,user.getUserName());
			}
			int isSuccess = myInfoService.updateSelfIntro(selfintroduction, uid);
			if(isSuccess==1){
				result.put("msg", "保存自我介绍成功");
				result.put("code", "1");
			}else{
				result.put("msg", "保存自我介绍失败");
				result.put("code", "0");
			}
		}else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value = "selfInfo/updateSpecialty", method = RequestMethod.POST,produces ="application/json;charset=UTF-8")
	public @ResponseBody String updateSpecialty(HttpServletRequest request,
			@RequestParam(value = "uid", required = false) String uid,
			@RequestParam(value = "specialty") String specialty) throws UnsupportedEncodingException {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if(user!=null){
			uid = user.getId();
			logger.info(""); 
			int isArtist = myPerformanceService.isArtist(uid);
			if(isArtist==0){
				isArtist = myInfoService.newArtist(uid,user.getUserName());
			}
			int isSuccess = myInfoService.updateSpecialty(specialty, uid);
			if(isSuccess==1){
				result.put("msg", "保存特长成功");
				result.put("code", "1");
			}else{
				result.put("msg", "保存特长失败");
				result.put("code", "0");
			}
		}else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	
	@RequestMapping(value = "selfInfo/updateType", method = RequestMethod.GET,produces ="application/json;charset=UTF-8")
	public @ResponseBody String updateType(HttpServletRequest request,
			@RequestParam(value = "uid", required = false) String uid,
			@RequestParam(value = "type") String type) throws UnsupportedEncodingException {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if(user!=null){
			uid = user.getId();
			logger.info(""); 
			int isArtist = myPerformanceService.isArtist(uid);
			if(isArtist==0){
				isArtist = myInfoService.newArtist(uid,user.getUserName());
			}
			int isSuccess = myInfoService.updateType(type, uid);
			if(isSuccess==1){
				result.put("msg", "保存艺人类型成功");
				result.put("code", "1");
			}else{
				result.put("msg", "保存艺人类型失败");
				result.put("code", "0");
			}
		}else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	@RequestMapping(value = "selfInfo/updateMore", method = RequestMethod.POST,produces ="application/json;charset=UTF-8")
	public @ResponseBody String updateMore(
			HttpServletRequest request,
			@RequestBody byte[] bytes) {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		String uid;
		if(user!=null){
			uid = user.getId();
			logger.info(""); 
			String key = request.getParameter("key");
			JSONObject jsonobject = JSONObject.fromObject(key);
			ArtistSelfMoreInfo perfBean1= (ArtistSelfMoreInfo)JSONObject.toBean(jsonobject,ArtistSelfMoreInfo.class);
			int isArtist = myPerformanceService.isArtist(uid);
			if(isArtist==0){
				isArtist = myInfoService.newArtist(uid,user.getUserName());
			}
			perfBean1.setId(user.getId());
			if(perfBean1.getSex().equals("男")){
				perfBean1.setSex("1");
			}else{
				perfBean1.setSex("2");
			}
			int isSuccess = myInfoService.updateMore(perfBean1);
			if(isSuccess==1){
				result.put("msg", "保存艺人更多信息成功");
				result.put("code", "1");
			}else{
				result.put("msg", "保存艺人更多信息失败");
				result.put("code", "0");
			}
		}else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	@RequestMapping(value = "selfInfo/updateSalary", method = RequestMethod.POST,produces ="application/json;charset=UTF-8")
	public @ResponseBody String updateMinSalary(HttpServletRequest request,
			@RequestParam(value = "uid", required = false) String uid,
			@RequestParam(value = "minsalary") Integer minsalary) throws UnsupportedEncodingException {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if(user!=null){
			uid = user.getId();
			logger.info(""); 
			int isArtist = myPerformanceService.isArtist(uid);
			if(isArtist==0){
				isArtist = myInfoService.newArtist(uid,user.getUserName());
			}
			int isSuccess = myInfoService.updateMinSalary(minsalary, uid);
			if(isSuccess==1){
				result.put("msg", "保存艺人类型成功");
				result.put("code", "1");
			}else{
				result.put("msg", "保存艺人类型失败");
				result.put("code", "0");
			}
		}else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	@RequestMapping(value = "selfInfo/updateCid/{cid}/{deviceToken}", method = RequestMethod.GET,produces ="application/json;charset=UTF-8")
	public @ResponseBody String updateCid(HttpServletRequest request,
			@PathVariable(value = "cid") String cid,
			@PathVariable(value = "deviceToken") String deviceToken) throws UnsupportedEncodingException {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if(user!=null){
			String uid = user.getId();
			logger.info(""); 
			int isSuccess = myInfoService.updateCidAndDeviceToken(cid, uid,deviceToken);
			if(isSuccess==1){
				result.put("msg", "维护用户推送信息成功");
				result.put("code", "1");
			}else{
				result.put("msg", "维护用户推送信息失败");
				result.put("code", "0");
			}
		}else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	/*
	 * 获取用户的实名认证状态
	 */
	
	@RequestMapping(value = "authState/get", method = RequestMethod.GET,produces ="application/json;charset=UTF-8")
	public @ResponseBody String authState(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if(user!=null){
			String uid = user.getId();
			logger.info("读取用户的实名状态！"); 
			/*
			 * authstate: 实名认证状态，-2未提交，-1认证未通过，0认证中，1通过审核
			 * auditresult:审核结果:通过，拒绝，待审核
			 * 
			 */
			int authState = myInfoService.getAuthStateOfUser(uid);
			AuthState authState1 = new AuthState();
			if(authState==-2){
				result.put("msg", "用户尚未提交认证文件");
				result.put("code", "-3");
			}else{
				if(authState==-1){
					result.put("msg", "实名认证审核未通过");
					result.put("code", "-2");
				}else{
					if(authState==1){
						authState1 = myInfoService.getAuthOfUser(uid);
						String iDnum = authState1.getIdNum().substring(0, 3)+"******"+authState1.getIdNum().substring(authState1.getIdNum().length()-3, authState1.getIdNum().length());
						authState1.setIdNum(iDnum);
						authState1.setAuditresult("通过");
						result.put("AuthState", authState1);
						result.put("msg", "实名认证审核通过");
						result.put("code", "1");
						
					}else{
						result.put("msg", "实名认证进行中");
						result.put("code", "0");
					}
				}
			
				
			}
		}else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	
	/*
	 * 获取用户的实名,用于进行银行卡绑定
	 */
	
	@RequestMapping(value = "authName/get", method = RequestMethod.GET,produces ="application/json;charset=UTF-8")
	public @ResponseBody String authName(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if(user!=null){
			String uid = user.getId();
			logger.info("读取用户的实名！"); 
			/*
			 * authstate: 实名认证状态0表示未认证，1表示已经认证，2表示待认证,3表示认证未通过
			 * auditresult:审核结果:通过，拒绝，待审核
			 * 
			 */
			int authState = myInfoService.getAuthStateOfUser(uid);
			AuthState authState1 = new AuthState();
			if(authState==-2||authState==-1||authState==0){
				result.put("msg", "实名认证未进行或未完成");
				result.put("code", "0");
			}else{	
				authState1 = myInfoService.getAuthOfUser(uid);
				String realName = authState1.getAuthName();
				result.put("realName", realName);
				result.put("msg", "读取实名成功");
				result.put("code", "1");
			}
		}else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	
	
	@RequestMapping(value = "artistListOfAtten/get", method = RequestMethod.GET,produces ="application/json;charset=UTF-8")
	public @ResponseBody String getArtistListOfAtten(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if(user!=null){
			String uid = user.getId();
			//String uid = "b508a19cb8b54e24ac1ed27577fadc7a";
			List<ArtistAttenListV> list = myInfoService.getArtistListOfAtten(uid);
			if(list==null){
				result.put("msg", "用户尚未进行关注");
				result.put("code", "0");
			}else{
				
				result.put("attenList", list);
				result.put("msg", "读取关注列表成功");
				result.put("code", "1");
			}
		}else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	/**
	 * 修改用户的实名认证状态
	 * @param uid						用户id
	 * authState=0表示待认证，1表示认证通过，2表示认证失败
	 * isauth: 是否实名认证，1表示认证，0表示未认证
	 * authstate: 实名认证状态0表示未认证，1表示已经认证，2表示待认证,3表示认证未通过
	 * auditresult:审核结果:通过，拒绝，待审核
	 * auditid			审核人id
	 * @return
	 */
	@RequestMapping(value = "authState/set", method = RequestMethod.GET,produces ="application/json;charset=UTF-8")
	public @ResponseBody String updateAuthState(HttpServletRequest request,
			@RequestParam(value = "authState") Integer authState,
			@RequestParam(value = "uid") String uid
			) throws UnsupportedEncodingException {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if(user!=null){
			String auditid = user.getId();
			int isUser = myPerformanceService.isArtist(uid);
			int isOk=1,isauth=0,authstate=1;
			String auditresult="";
			if(isUser==1){
				switch(authState){
				case 0:
					isauth = 0;
					authstate = 2;
					auditresult = "待审核";
					break;
				case 1:
					isauth = 1;
					authstate = 1;
					auditresult = "通过";
					break;
				case 2:
					isauth = 0;
					authstate = 3;
					auditresult = "拒绝";
					break;
				default:
					isOk = 0;
				}
				if(isOk>0){
					Integer ret = myInfoService.updateAuthState(uid, isauth,
							auditresult, authstate, auditid);
					if (ret != null && ret > 0) {
						result.put("msg", "实名认证成功");
						result.put("code", "1");
					} else {
						result.put("msg", "实名认证失败");
						result.put("code", "0");
					}
				}else{
					result.put("msg", "数据异常");
					result.put("code", "-3");
				}
			}
			else{
				result.put("msg", "非艺人不能进行实名认证");
				result.put("code", "-2");
			}
		}else{
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	public String getHead_upload_path() {
		return head_upload_path;
	}

	public void setHead_upload_path(String head_upload_path) {
		this.head_upload_path = head_upload_path;
	}

	public String getHead_get_path() {
		return head_get_path;
	}

	public void setHead_get_path(String head_get_path) {
		this.head_get_path = head_get_path;
	}

	public String getHead_home_upload_path() {
		return head_home_upload_path;
	}

	public void setHead_home_upload_path(String head_home_upload_path) {
		this.head_home_upload_path = head_home_upload_path;
	}

	public String getHead_home_get_path() {
		return head_home_get_path;
	}

	public void setHead_home_get_path(String head_home_get_path) {
		this.head_home_get_path = head_home_get_path;
	}

	public String getFile_tmp() {
		return file_tmp;
	}

	public void setFile_tmp(String file_tmp) {
		this.file_tmp = file_tmp;
	}

}
