/**
 * 创建日期：2017-1-11下午3:55:04
 * 修改日期：
 * 作者：邢传军
 * 账户管理Dao
 */
package com.xunyanhui.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xunyanhui.bean.AccountBalance;
import com.xunyanhui.bean.AccountCardL;
import com.xunyanhui.bean.Bill;
import com.xunyanhui.model.Account;
import com.xunyanhui.model.AccountCard;
import com.xunyanhui.model.TransactionRecord;

public interface AccountDao {
	
	
	/**
	 * 新建用户账户 
	 * @param account
	 * @return
	 */
	public int newAccount(Account account);
	/**
	 * 读取指定账户的余额账户已uid为id 
	 * @param uid				用户id
	 * @return
	 */
	public AccountBalance getBalanceOfUser(@Param("uid") String uid);
	
	/**
	 * 检查用户是否存在帐户 
	 * @param uid				用户id
	 * @return   用户的账户id
	 */
	public String checkAccount(@Param("uid") String uid);
	/**
	 * 新建交易 
	 */
	public int newTR(TransactionRecord tr);
	/**
	 * 修改交易状态，根据交易id
	 * @param trid				交易id
	 * @param state				state
	 */
	public int updateTRState(@Param("trid") String trid,@Param("state") int state);
	/**
	 * 修改交易状态，根据演艺活动id，支付人id和收款人id
	 * @param paymentid				paymentid支付方id
	 * @param receiveid				receiveid收款方id
	 * @param performanceid			performanceid关联的演艺活动id
	 * @param state				state
	 * @return   影响的行的数量
	 */
	public int updateTRStateByPerformance(@Param("paymentid") String paymentid,
										@Param("receiveid") String receiveid,
										@Param("performanceid") String performanceid,
										@Param("state") int state);
	/**
	 * 绑定银行卡到用户账户上 
	 */
	public int bandCardOnAccount(AccountCard ac);
	/**
	 * 解除银行卡在用户账户上的绑定 
	 * @param uid				用户id
	 * @param cardno			银行卡卡号				
	 * @return
	 */
	public int unbandCardOnAccount(@Param("uid") String uid,@Param("cardno") String cardno);
	/**
	 * 解除银行卡在用户账户上的绑定 
	 * @param uid				用户id
	 * @param cardno			银行卡卡号				
	 * @return
	 */
	public int setMasterCardOfAccount(@Param("uid") String uid,@Param("cardno") String cardno);
	
	/**
	 * 读取用户绑定的银行卡列表 
	 * @param uid				用户id		
	 * @return
	 */
	public List<AccountCardL> bandCardList(@Param("uid") String uid);
	/**
	 * 检查银行卡是否绑定过 
	 * @param cardno			银行卡卡号				
	 * @return
	 */
	public AccountCard checkCardBand(@Param("cardno") String cardno);
	/**
	 * 获取客户的交易帐单
	 * @param uid				用户的id	
	 * @param startTime			账单过滤的开始时间	
	 * @param endTime			账单过滤的结束时间
	 * @param start				读取的账单开始位置
	 * @param length			读取的账单数量		
	 * @return
	 */
	public List<Bill> getBills(
			@Param("uid") String uid,
			@Param("startTime") String startTime,
			@Param("endTime") String endTime,
			@Param("start") int start,
			@Param("length") int length
			);
	/**
	 * 设置用户的支付密码
	 * @param uid				用户的id	
	 * @param password			新支付密码	
	 * @param oldpd				原有支付密码
	 * @return
	 */
	public int updatePassWordOfAccount(
			@Param("uid") String uid,
			@Param("password") String password,
			@Param("oldpd") String oldpd
			);
	/**
	 * 获取用户绑定的移动手机号
	 * @param uid				用户id
	 * @return   用户的账户id
	 */
	public String getMobile(@Param("uid") String uid);
	/**
	 * 检查用户的账户支付密码是否正确
	 * @param uid					用户id
	 * @param password				用户的支付密码
	 * @return   用户的账户id
	 */
	public String checkAccountPassword(@Param("uid") String uid,@Param("password") String password);
	/**
	 * 更新用户账户余额
	 * @param uid					用户id
	 * @param value					账户余额更新值,单位：分
	 */
	public Integer updateBalance(@Param("uid") String uid,@Param("value") int value);
	/**
	 * 根据绑定的银行卡的id读取银行卡信息，注意不是银行卡号
	 * @param accountcardid					银行卡账户id
	 */
	public AccountCard getCardInfo(@Param("accountcardid") String uid);
	

}
