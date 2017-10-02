/**
 * 创建日期：2016-12-16下午6:18:55
 * 修改日期：
 * 作者：邢传军
 * 目的：用于与实名认证相关的数据处理
 */
package com.xunyanhui.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xunyanhui.model.ActiveMessageList;
import com.xunyanhui.model.User;
import com.xunyanhui.service.AuthFileService;
import com.xunyanhui.service.MyInfoService;
import com.xunyanhui.service.SystemMessageService;
import com.xunyanhui.service.SystemPrivateMessageService;
import com.xunyanhui.service.UserService;
import com.xunyanhui.utils.FileUtil;
import com.xunyanhui.utils.UUidUtil;

@Controller
@RequestMapping(value = "/realname")
public class RealNameController {
	/** 日志实例 */
	private static final Logger logger = Logger
			.getLogger(RealNameController.class);
	// 获取配置中的实名认证文件上传路径和网络访问位置
	private @Value("#{test['REPOSITORY_PATH_REALNAME']}")
	String file_upload_path;
	private @Value("#{test['IMAGE_BASE_URL_REALNAME']}")
	String file_get_path;
	// 获取配置中的文件的临时中转路径
	private @Value("#{test['REPOSITORY_PATH_TEMP']}")
	String file_tmp;
	@Resource
	AuthFileService authFileService;
	@Resource
	MyInfoService myinfoService;
	

	/*
	 * (分页模式)读取系统中存储的活动消息列表 token 用户认证token uid 当前用户id(即接收信息的用户id) type 实名认证文件类型
	 * authname 用户实名 idnum 实名认证证件号码 ,@RequestParam("type") String
	 * type,@RequestParam("token") String token,@RequestParam("uid") String
	 * uid,@RequestParam("authname") String authname,@RequestParam("idnum")
	 * String idnum
	 */
	@RequestMapping(value = "/uploadFile/new.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String uploadRealNameFile(HttpServletRequest request,
			@RequestParam(value = "myname", required = false) String authname,
			@RequestParam(value = "mynum", required = false) String idnum)
			throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if (user != null) {
			String myName = "";
			String mynum = "";
			String uid = "";
			// 上传文件目录
			String uploadDir = this.getFile_upload_path();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置内存区块大小4KB
			factory.setSizeThreshold(4 * 1024);
			// 设置暂存容器，当上传文件大于设置的内存块大小时，用暂存容器做中转
			factory.setRepository(new File(this.getFile_tmp()));
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			fileUpload.setSizeMax(1024 * 1024 * 100);
			// fileUpload.setFileSizeMax(1024 * 1024 * 10);
			List<FileItem> fileItemList = null;
			try {
				fileItemList = fileUpload.parseRequest(request);
				System.out.println("fileItemList" + fileItemList.size());
			} catch (Exception e) {
				e.printStackTrace();
			}

			Iterator<FileItem> fileItemIterator = fileItemList.iterator();
			FileItem fileItem = null;
			int isOk = 0;
			while (fileItemIterator.hasNext()) {
				fileItem = fileItemIterator.next();
				// 普通文件框上传
				if (fileItem.isFormField()) {
					String filedName = fileItem.getFieldName();
					String filedValue = fileItem.getString("utf-8");// 编码格式
					if (filedName.equals("myname")) {
						myName = filedValue;
					}
					if (filedName.equals("uid")) {
						uid = filedValue;
					}
					if (filedName.equals("mynum")) {
						mynum = filedValue;
					}
					System.out.println(filedName);// 文件框名称
					System.out.println(filedValue);// 文件的值
				} else {
					String filedName = fileItem.getFieldName();// 文件上传框的名称
					// 获取文件上传的文件名
					String OriginalFileName = FileUtil.takeOutFileName(fileItem
							.getName());
					System.out.println("原始文件名：" + OriginalFileName);
					if (!"".equals(OriginalFileName)) {
						// 根据上传的文件名重新命名
						String newFileName = FileUtil.getNewFileName(
								OriginalFileName, user.getId(), 1);
						System.out.println("重新名：" + newFileName);
						File writeToFile = new File(uploadDir + File.separator
								+ newFileName);
						try {
							fileItem.write(writeToFile);
							
								isOk =authFileService.insertAuthFile(UUidUtil.getUUid(),
									"1", myName, mynum, newFileName,
									this.getFile_get_path(), user.getId());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			/**
			 * 修改用户的实名认证状态
			 * @param uid						用户id
			 * isauth: 是否实名认证，1表示认证，0表示未认证
			 * authstate: 实名认证状态，-2未提交，-1认证未通过，0认证中，1通过审核
			 * auditresult:审核结果:通过，拒绝，待审核
			 * auditid			审核人id
			 * @return
			 */
			if(isOk ==1){
				isOk= myinfoService.updateAuthState(user.getId(), 0, "认证中", 0, user.getId());
				if(isOk ==1){
					result.put("result", "维护状态成功");
					result.put("code", "1");
				}
				else{
					result.put("result", "维护状态失败");
					result.put("code", "0");
				}
			}
			else{
				result.put("result", "上传文件失败");
				result.put("code", "-2");
			}
			
		}
		else{
			result.put("result", "success");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

	@RequestMapping("/newFile.json")
	public String fileUpload3() throws IOException {
		System.out.println(this.getFile_upload_path());
		return "/success";
	}

	public String getFile_upload_path() {
		return file_upload_path;
	}

	public void setFile_upload_path(String file_upload_path) {
		this.file_upload_path = file_upload_path;
	}

	public String getFile_get_path() {
		return file_get_path;
	}

	public void setFile_get_path(String file_get_path) {
		this.file_get_path = file_get_path;
	}

	public String getFile_tmp() {
		return file_tmp;
	}

	public void setFile_tmp(String file_tmp) {
		this.file_tmp = file_tmp;
	}
}
