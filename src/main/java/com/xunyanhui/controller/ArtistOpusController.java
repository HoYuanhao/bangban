/**
 * 创建日期：2016-12-20下午2:14:27
 * 修改日期：
 * 作者：邢传军
 * 目的：实现小样或作品的操作类
 */
package com.xunyanhui.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunyanhui.bean.ArtiArtist;
import com.xunyanhui.bean.EvaluationOpus;
import com.xunyanhui.bean.MyArtistOpusL;
import com.xunyanhui.model.ArtistDetailView;
import com.xunyanhui.model.ArtistOpus;
import com.xunyanhui.model.User;
import com.xunyanhui.service.ActiveMessageService;
import com.xunyanhui.service.ArtistOpusService;
import com.xunyanhui.service.EvaluateService;
import com.xunyanhui.service.MyPerformanceService;
import com.xunyanhui.service.UserService;
import com.xunyanhui.utils.FileUtil;
import com.xunyanhui.utils.UUidUtil;

@Controller
@RequestMapping(value = "/artistOpus")
public class ArtistOpusController {
	/** 日志实例 */
	private static final Logger logger = Logger
			.getLogger("ArtistOpusController");
	// 获取配置中的小样或作品数据文件上传路径和网络访问位置
	private @Value("#{test['REPOSITORY_PATH_DEMO']}")
	String demo_upload_path;
	private @Value("#{test['BASE_URL_DEMO']}")
	String demo_get_path;
	// 获取配置中的小样或作品列表图片上传路径和网络访问位置
	private @Value("#{test['REPOSITORY_PATH_DEMO_PIC']}")
	String demo_pic_upload_path;
	private @Value("#{test['IMAGE_BASE_URL_DEMO_PIC']}")
	String demo_pic_get_path;
	// 获取配置中的小样或作品详情图片上传路径和网络访问位置
	private @Value("#{test['REPOSITORY_PATH_DEMO_PIC_HOME']}")
	String home_pic_upload_path;
	private @Value("#{test['IMAGE_BASE_URL_DEMO_PIC_HOME']}")
	String home_pic_get_path;
	// 获取配置中的文件的临时中转路径
	private @Value("#{test['REPOSITORY_PATH_TEMP']}")
	String file_tmp;
	@Autowired
	private ArtistOpusService artistOpusService;
	@Autowired
	private EvaluateService evaluateService;
	@Autowired
	private MyPerformanceService myPerformanceService;

	/*
	 * 上传小样
	 */
	@RequestMapping(value = "/uploadDemo/new.json", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String newDemo(HttpServletRequest request,
			@RequestParam("demoName") String demoName,
			@RequestParam("fdesc") String fdesc,
			@RequestParam("type") String type,
			@RequestParam("label") String label,
			@RequestParam("kind") String kind,
			@RequestParam("filename") String filename,
			@RequestParam("filehome") String filehome
			)
			throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			/*{
			"opusId": "XMTMzOTU4MDIzMg==", // 视频id
			"title": "中国好声音第四季10强争夺战 张磊47：4秒杀李安", // 标题
			"category": "娱乐", // 类别
			"tags": "李安,张磊,中国好声音,中国好声音第四季,中国好声音第四季10强争夺战", // 标签
			"desc": "第四季《中国好声音》(在线观看)启动4年来首次“导师考核”混战赛制，这也是世界《好声音》史上的特例。“姐弟联盟”——那英 和周杰伦不得不斗智斗勇，拆伙厮杀。最后，那英组张磊、孙伯纶和周杰伦组李幸倪、陈梓童、李安进入“鸟巢冲刺夜”。期间，民谣再次笑傲《好声音》，不过，张磊和李安的大比分落差，也让网友眼镜大跌。而轮番上台的华裔学员们，纷纷“表白”中国，让人感动。  昨晚，两位导师的8位学员捉对厮杀，让看客们最错愕的，无疑是第3组出场的张磊和李安。当包括音乐人、乐评人、电台DJ和媒体在内的51位评审，最终投出47：4的结果时，网友们炸锅了，一部分网友认同：“张磊的确唱进了我的心。”而另一部分则产",
			"homepic": "https://r1.ykimg.com/0542040855FCF4826A0A417EE1D485CF", // 封面
			"type": "小样" // 小样 / 作品
			}
			 */
			String opusId = UUidUtil.getUUid();
			ArtistOpus artistOpus = new ArtistOpus();
			artistOpus.setOid(opusId);
			artistOpus.setUid(user.getId());
			artistOpus.setName(demoName);
			artistOpus.setDescription(fdesc);
			artistOpus.setReleaseId(user.getId());
			System.out.println("type" + type);
			artistOpus.setType(Integer.valueOf(type));
			// artistOpus.setType(21);
			artistOpus.setFileName(filename);
			artistOpus.setFilePath(this.getDemo_get_path());
			artistOpus.setLabel(label);
			artistOpus.setKind(kind);
			artistOpus.setHomePic(filehome);
			int ret = artistOpusService.insertArtistOpus(artistOpus);
			if (ret == 1) {
				result.put("msg", "小样上传/作品成功！");
				result.put("code", "1");
			} else {
				result.put("msg", "小样上传/作品失败！");
				result.put("code", "0");
			}

		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	/*
	 *  未修改前自主上传小样/作品音视频的action
	 *  HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			String demoName = "";// 作品或小样的名称
			String fdesc = "";// 小样或作品的描述
			String uid = "";// 上传小样或作品的用户id
			String type = "";// 小样或作品的类别，11表示小样视频，12表示小样音频，21表示作品视频，22表示作品音频
			String label = "";// 小样或作品标签，例如：慢歌
			String kind = "";// 小样或作品分类，例如：民歌
			String newFileName = "";
			// 上传文件目录
			String uploadDir = this.getDemo_upload_path();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置内存区块大小4KB
			factory.setSizeThreshold(4 * 1024);
			// 设置暂存容器，当上传文件大于设置的内存块大小时，用暂存容器做中转
			factory.setRepository(new File(this.getFile_tmp()));
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			fileUpload.setSizeMax(1024 * 1024 * 1024);
			// fileUpload.setFileSizeMax(1024 * 1024 * 10);
			List<FileItem> fileItemList = null;
			try {
				fileItemList = fileUpload.parseRequest(request);
				System.out.println("fileItemList" + fileItemList.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			int ret = 0;
			String opusId = UUidUtil.getUUid();
			Iterator<FileItem> fileItemIterator = fileItemList.iterator();
			FileItem fileItem = null;
			while (fileItemIterator.hasNext()) {
				fileItem = fileItemIterator.next();
				// 普通文件框上传
				if (fileItem.isFormField()) {
					String filedName = fileItem.getFieldName();
					String filedValue = fileItem.getString("utf-8");// 编码格式
					if (filedName.equals("demoName")) {
						demoName = filedValue;
					}
					if (filedName.equals("fdesc")) {
						fdesc = filedValue;
					}
					if (filedName.equals("uid")) {
						uid = filedValue;
					}
					if (filedName.equals("type")) {
						type = filedValue;
					}
					if (filedName.equals("label")) {
						label = filedValue;
					}
					if (filedName.equals("kind")) {
						kind = filedValue;
					}
				} else {
					String filedName = fileItem.getFieldName();// 文件上传框的名称
					// 获取文件上传的文件名
					String OriginalFileName = FileUtil.takeOutFileName(fileItem
							.getName());
					System.out.println("原始文件名：" + OriginalFileName);
					if (!"".equals(OriginalFileName)) {
						// 根据上传的文件名重新命名
						newFileName = FileUtil.getNewFileName(OriginalFileName,
								opusId, 0);
						System.out.println("重新名：" + newFileName);
						File writeToFile = new File(uploadDir + File.separator
								+ newFileName);
						try {
							fileItem.write(writeToFile);

						} catch (Exception e) {
							e.printStackTrace();
							logger.error("文件写入失败！" + e.getMessage());
						}
					}
				}

			}
			ArtistOpus artistOpus = new ArtistOpus();
			artistOpus.setOid(opusId);
			artistOpus.setUid(user.getId());
			artistOpus.setName(demoName);
			artistOpus.setDescription(fdesc);
			artistOpus.setReleaseId(user.getId());
			System.out.println("type" + type);
			artistOpus.setType(Integer.valueOf(type));
			// artistOpus.setType(21);
			artistOpus.setFileName(newFileName);
			artistOpus.setFilePath(this.getDemo_get_path());
			artistOpus.setLabel(label);
			artistOpus.setKind(kind);
			ret = artistOpusService.insertArtistOpus(artistOpus);
			if (ret == 1) {
				result.put("msg", "小样上传/作品成功！");
				result.put("code", "1");
			} else {
				result.put("msg", "小样上传/作品失败！");
				result.put("code", "0");
			}

		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	 */
	/*
	 * 维护上传信息
	 */
	@RequestMapping(value = "/updateArtistOpus/{oid}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String updateArtistOpus(HttpServletRequest request,
			@PathVariable("oid") String oid,
			@RequestParam("demoName") String demoName,
			@RequestParam("fdesc") String fdesc,
			@RequestParam("type") String type,
			@RequestParam("label") String label,
			@RequestParam("kind") String kind,
			@RequestParam("filename") String filename,
			@RequestParam("filehome") String filehome
			)
			throws UnsupportedEncodingException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			
			ArtistOpus artistOpus = new ArtistOpus();
			artistOpus.setOid(oid);
			artistOpus.setUid(user.getId());
			artistOpus.setName(demoName);
			artistOpus.setDescription(fdesc);
			artistOpus.setReleaseId(user.getId());
			System.out.println("type" + type);
			artistOpus.setType(Integer.valueOf(type));
			// artistOpus.setType(21);
			artistOpus.setFileName(filename);
			artistOpus.setHomePic(filehome);
			artistOpus.setLabel(label);
			artistOpus.setKind(kind);
			int ret = artistOpusService.updateArtistOpus(artistOpus);
			if (ret==1) {
				result.put("msg", "维护小样/作品成功！");
				result.put("code", "1");
			} else {
				result.put("msg", "维护小样/作品失败！");
				result.put("code", "0");
			}

		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 维护小样的封面图片
	 */
	@RequestMapping(value = "/updateDemoHomePic/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String updateArtistOpusHomePic(HttpServletRequest request)
			throws UnsupportedEncodingException {
		String oid = null;// 存储待处理封面图片的小样oid
		String uid = null;// 存储小样所属的用户id
		// 上传文件目录
		String uploadDir = this.getHome_pic_upload_path();
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
		int ret = 0;
		String newFileName = null;
		Iterator<FileItem> fileItemIterator = fileItemList.iterator();
		FileItem fileItem = null;
		while (fileItemIterator.hasNext()) {
			fileItem = fileItemIterator.next();
			// 普通文件框上传
			if (fileItem.isFormField()) {
				String filedName = fileItem.getFieldName();
				String filedValue = fileItem.getString("utf-8");// 编码格式
				if (filedName.equals("oid")) {
					oid = filedValue;
				}
				if (filedName.equals("uid")) {
					uid = filedValue;
				}
			} else {
				String filedName = fileItem.getFieldName();// 文件上传框的名称
				// 获取文件上传的文件名
				String OriginalFileName = FileUtil.takeOutFileName(fileItem
						.getName());
				System.out.println("原始文件名：" + OriginalFileName);
				if (!"".equals(OriginalFileName)) {
					// 根据上传的文件名重新命名
					newFileName = FileUtil.getNewFileName(OriginalFileName,
							uid, 1);
					System.out.println("重新名：" + newFileName);
					File writeToFile = new File(uploadDir + File.separator
							+ newFileName);
					try {
						fileItem.write(writeToFile);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		}
		ArtistOpus artistOpus = new ArtistOpus();
		artistOpus.setOid(oid);
		artistOpus.setUid(uid);
		artistOpus.setHomePic(newFileName);
		artistOpus.setHomePicPath(this.getHome_pic_get_path());
		ret = artistOpusService.updateArtistOpusHomePic(artistOpus);
		return "{\"result\":\"" + ret + "\"}";
	}

	/**
	 * 对指定的小样或作品/演艺/进行点赞或好评
	 * 
	 * @param releaseid
	 *            评论人id，该评论人的用户id相同
	 * @param objectid
	 *            艺人头像的类型
	 * @param evaluatetype
	 *            被评价对象的类型，1表示艺人，2表示作品或小样，3表示演艺，其他待扩充
	 * @return
	 */
	@RequestMapping(value = "/comment/{objectid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String inGood(HttpServletRequest request,
			@PathVariable(value = "objectid") String objectid,
			@RequestParam(value = "comment") String comment,
			@RequestParam(value = "uid", required = false) String uid)
			throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		logger.info("");
		if (user != null) {
			String autuorId = evaluateService.getOpusAuthor(objectid);
			String uid1 = user.getId();
			if (autuorId.equals(uid1) || autuorId == null) {
				result.put("msg", "作者不能自评或小样不存在！");
				result.put("code", "-2");
			} else {
				Integer inGood;
				inGood = evaluateService.newCommentArtist(UUidUtil.getUUid(),
						objectid, uid1, 2, comment, autuorId);
				if (inGood > 0) {
					result.put("msg", "操作成功");
					result.put("code", "1");
				} else {
					result.put("msg", "操作失败");
					result.put("code", "0");
				}
			}
		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 获取用户小样/作品详情
	 */
	@RequestMapping(value = "getOpusById/{objectid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getOpusById(
			@PathVariable(value = "objectid") String objectid,
			@RequestParam(value = "token", required = false) String token,
			HttpServletRequest request,
			@RequestBody byte[] bytes) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (objectid != null) {
			MyArtistOpusL myArtistOpusL = myPerformanceService
					.getSelfOpusById(objectid);
			List<EvaluationOpus> postList = myPerformanceService
					.getPostOfSelfOpus(objectid);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute(UserService.LOGIN_USER);
			String author = evaluateService.getOpusAuthor(objectid);
			if (myArtistOpusL != null&&author!=null) {
				if(user!=null){
					if(author.equals(user.getId())){
						result.put("author", "1");
					}
					else{
						result.put("author", "0");
						Integer isGood = evaluateService.getGoodLevel(objectid, user.getId(), 2);
						if(isGood==null){
							isGood = 0;
						}
						result.put("isGood", isGood);
					}
				}
				myArtistOpusL.setEvaluationOpusList(postList);
				artistOpusService.incPlayNum(objectid);
				result.put("result", myArtistOpusL);
				result.put("code", "1");
				result.put("msg", "读取数据成功");
					
			} else {
				result.put("code", "0");
				result.put("msg", "读取数据失败！");
			}
		}
		else{
			result.put("code", "-1");
			result.put("msg", "数据参数异常失败！");
		}
		return JSONObject.fromObject(result).toString();
	}

	public String getDemo_upload_path() {
		return demo_upload_path;
	}

	public void setDemo_upload_path(String demo_upload_path) {
		this.demo_upload_path = demo_upload_path;
	}

	public String getDemo_get_path() {
		return demo_get_path;
	}

	public void setDemo_get_path(String demo_get_path) {
		this.demo_get_path = demo_get_path;
	}

	public String getDemo_pic_upload_path() {
		return demo_pic_upload_path;
	}

	public void setDemo_pic_upload_path(String demo_pic_upload_path) {
		this.demo_pic_upload_path = demo_pic_upload_path;
	}

	public String getDemo_pic_get_path() {
		return demo_pic_get_path;
	}

	public void setDemo_pic_get_path(String demo_pic_get_path) {
		this.demo_pic_get_path = demo_pic_get_path;
	}

	public String getHome_pic_upload_path() {
		return home_pic_upload_path;
	}

	public void setHome_pic_upload_path(String home_pic_upload_path) {
		this.home_pic_upload_path = home_pic_upload_path;
	}

	public String getHome_pic_get_path() {
		return home_pic_get_path;
	}

	public void setHome_pic_get_path(String home_pic_get_path) {
		this.home_pic_get_path = home_pic_get_path;
	}

	public String getFile_tmp() {
		return file_tmp;
	}

	public void setFile_tmp(String file_tmp) {
		this.file_tmp = file_tmp;
	}
}
