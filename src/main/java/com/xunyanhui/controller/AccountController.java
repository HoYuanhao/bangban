/**
 * 创建日期：2017-1-11下午4:06:09
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xunyanhui.bean.AccountBalance;
import com.xunyanhui.bean.AccountCardL;
import com.xunyanhui.bean.AuthState;
import com.xunyanhui.bean.BeeCloudMessage;
import com.xunyanhui.bean.Bill;

import com.xunyanhui.model.AccountCard;
import com.xunyanhui.model.TransactionRecord;
import com.xunyanhui.model.User;
import com.xunyanhui.service.AccountService;
import com.xunyanhui.service.MyInfoService;
import com.xunyanhui.service.SmsSendService;
import com.xunyanhui.service.UserService;
import com.xunyanhui.utils.UUidUtil;

@Controller
@RequestMapping("/")
public class AccountController {
	/** 日志实例 */
	private static final Logger logger = Logger
			.getLogger(AccountController.class);
	/*
	 * beecloud的相关信息
	 */
	private static String app_id = "7d722e6a-2dad-437d-8bf7-3c76b1e4ef70";
	private static String test_secret = "d28dc2e5-a642-4723-8d22-43697bf21775";
	private static int PAGE_LENGTH = 5;
	@Resource
	AccountService accountService;
	@Resource
	MyInfoService myInfoService;
	@Resource
	SmsSendService smsSendService;

	@RequestMapping(value = "account/getBalance", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getBalance(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			String uid = user.getId();
			AccountBalance accountBalance = accountService
					.getBalanceOfUser(uid);
			if (accountBalance == null) {
				result.put("msg", "请联系管理员或检查网络！");
				result.put("code", "0");
			} else {
				if (accountBalance.getPassWord() == null
						|| accountBalance.getPassWord().equals("")) {
					accountBalance.setPassWord("0");// 未设置支付密码
				} else {
					accountBalance.setPassWord("1");// 以设置支付密码
				}
				result.put("balance", accountBalance);
				result.put("msg", "读取成功！");
				result.put("code", "1");
			}
		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 用户充值，
	 * 1、用户向平台充值，首先选择支付渠道向平台发出申请，然后平台生成订单将订单写入平台数据表
	 * 2、平台生成订单信息返回到前端界面，前端向支付渠道发出支付请求，支付平台处理后返回结果给前端 
	 * 3、前端根据返回结果进行处理显示。
	 * 4、后台webhook程序接受beeclode的返回结果更新平台的充值请求状态，并向用户发送短信和推送消息
	 */
	@RequestMapping(value = "account/inAccount/{money}/{channel}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String inAccount(HttpServletRequest request,
			@PathVariable("money") int money,
			@PathVariable("channel") String channel,
			@RequestParam(value = "desc", required = false) String desc) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			String uid = user.getId();
			String accountId = accountService.checkAccount(uid);
			if (accountId == null || accountId.equals("")) {
				result.put("msg", "用户尚未在平台开立账户");
				result.put("code", "-2");
			} else {
				TransactionRecord tr = new TransactionRecord();
				Date date = new Date();
				long timestamp = date.getTime();
				String trid = uid.substring(0, 5) + timestamp;
				tr.setTrid(trid);
				tr.setPaymentId(uid);
				tr.setReceiveId("1");
				tr.setDescription("平台充值");
				tr.setChannel(channel);
				tr.setMoney(money);
				tr.setPerformanceId("1");
				tr.setType(1);
				tr.setState(1);
				tr.setTimestamp(Long.valueOf(timestamp).toString());
				int ret = accountService.newTR(tr);
				if (ret == 1) {
					BeeCloudMessage returnMessage = new BeeCloudMessage();
					String app_id = AccountController.app_id;
					returnMessage.setApp_id(app_id);
					returnMessage.setTimestamp(timestamp);
					String app_sign = DigestUtils.md5Hex(app_id + timestamp
							+ AccountController.test_secret);
					returnMessage.setApp_sign(app_sign);// 加密签名,算法:
														// md5(app_id+timestamp+app_secret)，32位16进制格式,不区分大小写
					returnMessage.setChannel(channel);
					returnMessage.setTotal_fee(money);// 单位是分，
					returnMessage.setBill_no(trid);
					returnMessage.setTitle("充值");
					result.put("beecloud", returnMessage);
					result.put("msg", "读取成功！");
					result.put("code", "1");
					System.out.println("申请处理："
							+ JSONObject.fromObject(result).toString());
				} else {
					result.put("msg", "充值申请失败！");
					result.put("code", "-2");
				}
			}
		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 用户提现，系统该请求后将生成提现请求数据记录供后台操作人员审核
	 * 审核后通过网银交易进行提现转款，提现成功后改变提现申请订单状态并用推送和短信通知用户
	 * 当前提现渠道默认为“bank”，即表示使用网银操作的形式来将用户申请的提现款打入到用户绑定的主银行卡中 将来待扩充了其他提现方式后进一步确定
	 */
	@RequestMapping(value = "account/outAccount/{money}/{channel}/{cardid}/{password}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String outAccount(HttpServletRequest request,
			@PathVariable("money") int money,
			@PathVariable("channel") String channel,
			@PathVariable("cardid") String cardid,
			@PathVariable("password") String password,
			@RequestParam(value = "desc", required = false) String desc) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			String uid = user.getId();
			AccountBalance accountBalance = accountService
					.getBalanceOfUser(uid);
			if (accountBalance == null) {
				result.put("msg", "用户尚未在平台开立账户");
				result.put("code", "-2");
			} else {
				if (password.equals(accountBalance.getPassWord())) {
					if (accountBalance.getMoney() < money) {
						result.put("msg", "可提现余额不足！");
						result.put("code", "-3");
					} else {
						AccountCard accountCard = accountService.getCardInfo(cardid);
						if (accountCard == null) {
							result.put("msg", "银行卡号未绑定！");
							result.put("code", "-4");
						} else {
							Integer updateBalance = accountService
									.updateBalance(uid, -money);
							if (updateBalance == 1) {
								TransactionRecord tr = new TransactionRecord();
								Date date = new Date();
								String trid = uid + date.toString();
								tr.setTrid(trid);
								tr.setPaymentId("1");
								tr.setReceiveId(uid);
								tr.setReceivecardId(accountCard.getCardNo());
								tr.setDescription(desc);
								tr.setState(1);
								tr.setChannel(accountCard.getBank());
								tr.setMoney(money);
								tr.setPerformanceId("1");
								tr.setType(2);
								int ret = accountService.newTR(tr);
								if (ret == 1) {
									result.put("msg", "提现订单创建成功！");
									result.put("code", "1");
								} else {
									result.put("msg", "提现订单创建失败！");
									result.put("code", "0");
								}
							} else {
								result.put("msg", "支付密码异常");
								result.put("code", "-5");
							}
						}
					}
				} else {
					result.put("msg", "支付密码异常");
					result.put("code", "-5");
				}
			}
		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * insert
	 * accountCard(accountcardid,uid,cardno,bank,bandtime,ismaster,cardname)
	 * values(#{accountCardId},#{uid},#{cardNo},#{bank},now(),0,#{cardName})
	 */
	/*
	 * 绑定银行卡到用户账户上
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "account/bandCard/{cardno}/{cardname}/{bank}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String bandCardOnAccount(HttpServletRequest request,
			@PathVariable("cardno") String cardno,
			@PathVariable("cardname") String cardname,
			@PathVariable("bank") String bank) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			String uid = user.getId();
			String accountId = accountService.checkAccount(uid);
			if (accountId == null || accountId.equals("")) {
				result.put("msg", "用户尚未在平台开立账户");
				result.put("code", "-2");
			} else {
				AuthState authstate = myInfoService.getAuthOfUser(uid);
				if (authstate != null
						&& authstate.getAuditresult().equals("通过")) {
					AccountCard ac = new AccountCard();
					ac = accountService.checkCardBand(cardno);
					if (ac == null && authstate.getAuthName().equals(cardname)) {
						ac.setBank(bank);
						ac.setUid(uid);
						ac.setCardNo(cardno);
						ac.setCardName(cardname);
						ac.setAccountCardId(UUidUtil.getUUid());
						ac.setAccound_id(accountId);
						int ret = accountService.bandCardOnAccount(ac);
						if (ret == 1) {
							result.put("msg", "绑定银行卡成功！");
							result.put("code", "1");
						} else {
							result.put("msg", "绑定银行卡失败！");
							result.put("code", "0");
						}
					} else {
						result.put("msg", "银行卡已经被绑定/卡用户名与用户实名认证名不一致！");
						result.put("code", "-3");
					}
				} else {
					result.put("msg", "用户尚未实名认证！");
					result.put("code", "-4");
				}

			}
		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 解除银行卡与用户账户的绑定
	 */
	@RequestMapping(value = "account/unbandCard/{cardno}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String unbandCard(HttpServletRequest request,
			@PathVariable("cardno") String cardno) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			String uid = user.getId();
			String accountId = accountService.checkAccount(uid);
			if (accountId == null || accountId.equals("")) {
				result.put("msg", "用户尚未在平台开立账户");
				result.put("code", "-2");
			} else {
				int ret = accountService.unbandCardOnAccount(uid, cardno);
				if (ret == 1) {
					result.put("msg", "解除绑定银行卡成功！");
					result.put("code", "1");
				} else {
					result.put("msg", "解除绑定银行卡失败！");
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
	 * 解除银行卡与用户账户的绑定
	 */
	@RequestMapping(value = "account/setMasterCard/{cardno}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String setMasterCard(HttpServletRequest request,
			@PathVariable("cardno") String cardno) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			String uid = user.getId();
			String accountId = accountService.checkAccount(uid);
			if (accountId == null || accountId.equals("")) {
				result.put("msg", "用户尚未在平台开立账户");
				result.put("code", "-2");
			} else {
				int ret = accountService.setMasterCardOfAccount(uid, cardno);
				if (ret == 1) {
					result.put("msg", "设置默认银行卡成功！");
					result.put("code", "1");
				} else {
					result.put("msg", "设置默认银行卡失败！");
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
	 * 获取用户绑定的银行卡列表
	 */
	@RequestMapping(value = "account/bandCardList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String bandCardList(HttpServletRequest request,
			@RequestParam(value = "uid", required = false) String uid) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			uid = user.getId();

			String accountId = accountService.checkAccount(uid);
			if (accountId == null || accountId.equals("")) {
				result.put("msg", "用户尚未在平台开立账户");
				result.put("code", "-2");
			} else {
				int authstate = myInfoService.getAuthStateOfUser(uid);
				System.out.println("authstate:"+authstate);
				if (authstate ==1) {
					List<AccountCardL> list = accountService.bandCardList(uid);
					if (list != null && list.size() != 0) {
						for (int i = 0; i < list.size(); i++) {
							String cardNo = list.get(i).getCardNo();
							cardNo = cardNo.substring(0, 4)
									+ " **** **** **** **"
									+ cardNo.substring(cardNo.length() - 1);
							list.get(i).setCardNo(cardNo);
						}
						result.put("cardList", list);
						result.put("msg", "已绑定银行！");
						result.put("code", "1");
					} else {
						result.put("msg", "用户尚未绑定银行卡！");
						result.put("code", "0");
					}
					result.put("isAuth", "1");
					AuthState authstate1 = myInfoService.getAuthOfUser(uid);
					result.put("realName", authstate1.getAuthName());

				} else {
					result.put("isAuth", "0");
					result.put("msg", "用户未实名认证！");
					result.put("code", "-3");
				}
			}
		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 读取用户的账单列表
	 */
	@RequestMapping(value = "account/getBills/{timelevel}/{page}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getBills(HttpServletRequest request,
			@PathVariable("timelevel") int timelevel,
			@PathVariable("page") int page,
			@RequestParam(value = "uid", required = false) String uid) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			uid = user.getId();
			String startTime = null, endTime = null;
			SimpleDateFormat simpleFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date current = new Date();
			Date beforeTime = new Date();
			Calendar calendar = Calendar.getInstance(); // 得到日历
			calendar.setTime(current);// 把当前时间赋给日历

			switch (timelevel) {
			case 1:
				startTime = simpleFormat.format(current);
				calendar.add(Calendar.MONTH, -1); // 设置为前1月
				beforeTime = calendar.getTime(); // 得到前3月的时间
				endTime = simpleFormat.format(beforeTime);
				break;
			case 3:
				startTime = simpleFormat.format(current);
				calendar.add(Calendar.MONTH, -3); // 设置为前1月
				beforeTime = calendar.getTime(); // 得到前3月的时间
				endTime = simpleFormat.format(beforeTime);
				break;
			case 6:
				startTime = simpleFormat.format(current);
				calendar.add(Calendar.MONTH, -6); // 设置为前1月
				beforeTime = calendar.getTime(); // 得到前3月的时间
				endTime = simpleFormat.format(beforeTime);
				break;
			default:
			}
			// System.out.println("uid"+uid+",startTime:"+startTime+",endTime:"+endTime+",start:"+(page-1)*PAGE_LENGTH+",end:"+PAGE_LENGTH);
			List<Bill> billList = accountService.getBills(uid, startTime,
					endTime, (page - 1) * PAGE_LENGTH, PAGE_LENGTH);
			result.put("bills", billList);
			result.put("msg", "访问成功");
			result.put("code", "1");
		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 修改用户的支付密码，用原始支付密码进行修改 (String uid, String password, String oldpd)
	 */
	@RequestMapping(value = "account/updatePassWord", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String updatePassWordOfAccount(HttpServletRequest request,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "oldpd") String oldpd) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			String uid = user.getId();
			String accountId = accountService.checkAccount(uid);
			System.out.println(accountId);
			// password = MD5.MD5(password, uid);
			if (!accountId.equals(null) && !accountId.equals("")
					&& password.length() == 6) {
				int ret = accountService.updatePassWordOfAccount(uid, password,
						oldpd);
				if (ret == 1) {
					result.put("msg", "修改支付密码成功");
					result.put("code", "1");
				} else {
					result.put("msg", "原密码错误，修改支付密码失败！");
					result.put("code", "0");
				}
			} else {
				result.put("msg", "用户尚未建立帐户");
				result.put("code", "-2");
			}
		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 修改用户的支付密码，使用绑定的手机号接收验证码
	 */
	@RequestMapping(value = "account/updatePassWordByMobil", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String updatePassWordByMobil(HttpServletRequest request,
			@RequestParam(value = "password") String password) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			String uid = user.getId();
			String accountId = accountService.checkAccount(uid);
			if (!accountId.equals(null) && !accountId.equals("")) {
				String check = (String) session
						.getAttribute(AccountService.PAY_PASSWORD);
				if (check.equals("1")) {
					// password = MD5.MD5(password, uid);
					int ret = accountService.updatePassWordOfAccount(uid,
							password, null);
					if (ret == 1) {
						result.put("msg", "修改支付密码成功");
						session.removeAttribute(AccountService.PAY_PASSWORD);
						result.put("code", "1");
					} else {
						result.put("msg", "修改支付密码失败！");
						result.put("code", "0");
					}
				} else {
					result.put("msg", "验证码错误");
					result.put("code", "-3");
				}
			} else {
				result.put("msg", "用户尚未建立帐户");
				result.put("code", "-2");
			}
		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 验证用户的原始支付密码是否正确
	 */
	@RequestMapping(value = "account/checkPassWord", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String checkPassWord(HttpServletRequest request,
			@RequestParam(value = "password") String password) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(UserService.LOGIN_USER);
		Map<String, Object> result = new HashMap<String, Object>();
		if (user != null) {
			String uid = user.getId();
			// password = MD5.MD5(password, user.getId());
			String accountId = accountService.checkAccountPassword(uid,
					password);
			if (!(accountId == null) && !accountId.equals("")) {
				result.put("msg", "密码正确");
				result.put("code", "1");
			} else {
				result.put("msg", "密码不正确");
				result.put("code", "-2");
			}
		} else {
			result.put("msg", "未登录");
			result.put("code", "-1");
		}
		return JSONObject.fromObject(result).toString();
	}
	
	

}
