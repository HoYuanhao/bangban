/**
 * 创建日期：2017-1-4下午1:04:49
 * 修改日期：
 * 作者：邢传军
 * 目的：用于评价的处理
 */
package com.xunyanhui.controller;

import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunyanhui.bean.ArtistPerforScoreDetail;
import com.xunyanhui.bean.ArtistSelfMoreInfo;
import com.xunyanhui.jms.bean.MessageInfo;
import com.xunyanhui.jms.service.JMSProducer;
import com.xunyanhui.model.ArtistAnnouncement;
import com.xunyanhui.model.ArtistEvaluateType;
import com.xunyanhui.model.ArtistPerforEvaluate;
import com.xunyanhui.model.User;
import com.xunyanhui.service.ArtistOpusService;
import com.xunyanhui.service.EvaluateService;
import com.xunyanhui.service.MyPerformanceService;
import com.xunyanhui.service.UserService;
import com.xunyanhui.utils.UUidUtil;

@Controller
@RequestMapping("/")
public class EvaluateController {
	/** 日志实例 */
	private static final Logger logger = Logger
			.getLogger(EvaluateController.class);

	@Resource
	EvaluateService evaluateService;

	@Autowired
	private ArtistOpusService artistOpusService;

	 @Resource
	 private JMSProducer jMSProducer;
	 @Resource
	 private ActiveMQQueue queueDestination;

	/**
	 * 判断指定的用户对某一对象是否点赞或好评
	 * 
	 * @param request
	 * @param releaseid
	 *            评论人id，该评论人的用户id相同
	 * @param objectid
	 *            艺人头像的类型
	 * @param evaluatetype
	 *            被评价对象的类型，1表示艺人，2表示作品或小样，3表示演艺，其他待扩充
	 * @return
	 */
	@RequestMapping(value = "evaluate/isGood/{objectid}/{evaluatetype}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String isGood(HttpServletRequest request,
			@PathVariable(value = "objectid") String objectid,
			@PathVariable(value = "evaluatetype") int evaluatetype,
			@RequestParam(value = "uid", required = false) String uid)
			throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		logger.info("");
		// if (user != null) {
		Integer isGood = evaluateService.isGoodLevel(objectid, uid, 1);
		if (isGood == null) {
			isGood = 0;
		}
		result.put("msg", "上传成功");
		result.put("code", "1");
		result.put("isGood", isGood);
		// }
		// else{
		// result.put("msg", "未登录");
		// result.put("code", "-1");
		// }
		return JSONObject.fromObject(result).toString();
	}

	/**
	 * 对指定的小样或作品/演艺/进行点赞或好评
	 * 
	 * @param releaseid
	 *            评论人id，该评论人的用户id相同
	 * @param objectid
	 *            被评价的对象id
	 * @param evaluatetype
	 *            被评价对象的类型，1表示艺人，2表示作品或小样，3表示演艺，其他待扩充
	 * @return
	 */
	@RequestMapping(value = "evaluate/inGood/{objectid}/{evaluatetype}/{state}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String inGood(HttpServletRequest request,
			@PathVariable(value = "objectid") String objectid,
			@PathVariable(value = "evaluatetype") int evaluatetype,
			@PathVariable(value = "state") int state,
			@RequestParam(value = "uid", required = false) String uid)
			throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		if (user != null) {
			try {
				//被评价人
				String acceptid = "";
				if(evaluatetype==1){
					acceptid = objectid;
				}
				else{
					acceptid = evaluateService.getOpusAuthor(objectid);
				}
				int isGood = evaluateService.isGoodLevel(objectid,
						user.getId(), evaluatetype);
				 
				
				Integer inGood;
				if (isGood == 0) {
					System.out.println("dfdfdfd");
					inGood = evaluateService.newGoodLevel(UUidUtil.getUUid(),
							objectid, user.getId(), evaluatetype, state,acceptid);
				} else {
					System.out.println("dfdfdfd1111111111");
					inGood = evaluateService.inGoodLevel(objectid,
							user.getId(), evaluatetype, state);
				}
				if (inGood == null) {
					inGood = 0;
				}
				if (inGood > 0) {
					switch(evaluatetype){
					case 1:
						//对艺人
						evaluateService.updatePriseOfArtist(user.getId());
						break;
					case 2:
						//对小样,state为0表示差评，1表示好评
						if(state==1){
							//System.out.println("点赞;"+objectid+",uid"+user.getId());
							evaluateService.updatePriseOfOpus(+1, objectid, user.getId());
						}else{
							//System.out.println("取消:"+objectid+",uid"+user.getId());
							evaluateService.updatePriseOfOpus(-1, objectid, user.getId());
						}
						break;
					case 3:
						//对演艺活动
						break;
					}
					MessageInfo msgInfo = new MessageInfo();
					/*
					 * {"type":"8","sid":"sid","did":"did","value":"消息内容"}
					 * Sid表示被点赞/好评的小样id/艺人id Did 表示 1 点赞 2好评 Value 表示 0 取消点赞/差评
					 * 1点赞/好评
					 */
					msgInfo.setType(8);
					msgInfo.setSid(objectid);
					msgInfo.setValue(Integer.valueOf(state).toString());
					if (evaluatetype == 2)
						msgInfo.setDid("1");// 点赞，对小样或作品
					else
						msgInfo.setDid("2");// 好评，对演艺中的完成演艺活动的艺人
					/*
					 * 获得点赞或好评的时候是否推送信息
					 */
					//jMSProducer.sendMessage(queueDestination,
					//JSONObject.fromObject(msgInfo).toString());
					logger.info("进行了点赞/好评");
					result.put("msg", "操作成功");
					result.put("code", "1");
				} else {
					result.put("msg", "操作失败");
					result.put("code", "0");
				}
			} catch (Exception e) {
				result.put("msg", "未登录");
				result.put("code", "-1");
				return JSONObject.fromObject(result).toString();
			}

		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

	/**
	 * 读取某一艺人在完成某一演艺活动后得到的打分，好评及评论情况
	 * 
	 * @param performanceid
	 *            演艺活动id
	 * @param artistid
	 *            艺人id
	 * @return
	 */
	@RequestMapping(value = "getEvaluate/{artistid}/{performanceid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getEvaluate(HttpServletRequest request,
			@PathVariable(value = "artistid") String artistid,
			@PathVariable(value = "performanceid") String performanceid,
			@RequestParam(value = "uid", required = false) String uid)
			throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		logger.info("");
		if (user != null) {
			if (user != null) {
				uid = user.getId();
			}
			ArtistPerforScoreDetail scoreDetail = new ArtistPerforScoreDetail();
			List<ArtistPerforEvaluate> listScore = evaluateService
					.getScoreArtistOfPerfor(artistid, performanceid);

			if (listScore.size() == 0) {
				List<ArtistEvaluateType> scoreTypeList = evaluateService
						.getScoreList();
				for (int i = 0; i < scoreTypeList.size(); i++) {
					ArtistPerforEvaluate tmp = new ArtistPerforEvaluate();
					tmp.setUid(artistid);
					tmp.setEvaluateId(scoreTypeList.get(i).getEvaluateId());
					tmp.setPerformanceId(performanceid);
					tmp.setType(scoreTypeList.get(i).getType());
					tmp.setValue(0);
					listScore.add(tmp);
				}
				scoreDetail.setEvaluation("");
				scoreDetail.setGoodLevel(2);
				scoreDetail.setIsGood(0);

			} else {
				scoreDetail
						.setEvaluation(evaluateService
								.getCommentArtistOfPerfor(uid, artistid,
										performanceid));
				Integer googLevel = evaluateService.getGoodLevelOfPerfor(performanceid,
						uid, 3,artistid);
				logger.error("goodlevel" + googLevel);
				scoreDetail.setIsGood(1);
				scoreDetail.setGoodLevel(1);
				if (googLevel == null) {
					scoreDetail.setIsGood(0);
					googLevel = 2;
				}
				scoreDetail.setGoodLevel(googLevel);
			}
			scoreDetail.setScoreList(listScore);
			result.put("result", scoreDetail);
			result.put("msg", "获取数据成功！");
			result.put("code", "1");
		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

	/**
	 * 对某一艺人在完成某一演艺活动后得到的打分，好评及评论情况
	 * 
	 * @param performanceid
	 *            演艺活动id
	 * @param artistid
	 *            艺人id
	 * @return
	 */
	@RequestMapping(value = "setEvaluate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String setEvaluate(HttpServletRequest request, 
			@RequestBody byte[] bytes)
			throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		String uid = null;
		logger.info("");
		if (user != null) {
			if (user != null) {
				uid = user.getId();
			}
			String key = request.getParameter("key");
			JSONObject jsonobject = JSONObject.fromObject(key);
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("scoreList", ArtistPerforEvaluate.class);
			ArtistPerforScoreDetail perfBean1 = (ArtistPerforScoreDetail) JSONObject
					.toBean(jsonobject, ArtistPerforScoreDetail.class, classMap);
			System.out.println(JSONObject.fromObject(perfBean1).toString());
			List<ArtistPerforEvaluate> scorelist = perfBean1.getScoreList();
			String objectid = scorelist.get(0).getPerformanceId();
			String releaseid = uid;
			String artistid = scorelist.get(0).getUid();
			Integer goodLeveL = evaluateService.getGoodLevelOfPerfor(objectid,
					releaseid, 3,artistid);
			if (goodLeveL == null) {
				if (evaluateService.setEvaluate(releaseid, artistid, objectid, perfBean1)==1) {
					result.put("msg", "写入数据成功！");
					result.put("code", "1");
				} else {
					result.put("msg", "写入数据失败！");
					result.put("code", "0");
				}
			} else {
				result.put("msg", "不允许重复评论！");
				result.put("code", "-2");
			}

		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

	/**
	 * 对某一小样进行评论
	 * 
	 * @param artistOpusid
	 *            作品或小样的id
	 * @param artistid
	 *            艺人id
	 * @return
	 */
	@RequestMapping(value = "artistOpus/comment/{artistOpusid}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String comment(HttpServletRequest request,
			@PathVariable(value = "artistOpusid") String artistOpusid,
			@RequestParam(value = "comment") String comment)
			throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		String uid = null;
		if (user != null) {
			uid = user.getId();
			String uidofOPus = artistOpusService.checkUidOfOpus(artistOpusid);
			if (uidofOPus != null && uidofOPus.equals(uid)) {
				int ret = evaluateService.newCommentArtist(UUidUtil.getUUid(),
						artistOpusid, uid, 2, comment, uidofOPus);
				if (ret == 1) {
					result.put("msg", "写入数据成功！");
					result.put("code", "1");
				} else {
					result.put("msg", "写入数据失败！");
					result.put("code", "0");
				}

			} else {
				result.put("msg", "不能进行自评或小样或作品不存在！");
				result.put("code", "-2");
			}

		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

}
