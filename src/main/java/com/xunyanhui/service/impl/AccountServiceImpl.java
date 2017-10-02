/**
 * 创建日期：2017-1-11下午4:02:46
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyanhui.bean.AccountBalance;
import com.xunyanhui.bean.AccountCardL;
import com.xunyanhui.bean.Bill;
import com.xunyanhui.dao.AccountDao;
import com.xunyanhui.model.Account;
import com.xunyanhui.model.AccountCard;
import com.xunyanhui.model.TransactionRecord;
import com.xunyanhui.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	final Logger logger = Logger.getLogger("AccountService");
	@Autowired
	private AccountDao accountDao;
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.AccountService#newAccount(com.xunyanhui.model.Account)
	 */
	@Override
	public int newAccount(Account account) {
		// TODO Auto-generated method stub
		return accountDao.newAccount(account);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.AccountService#getBalanceOfUser(java.lang.String)
	 */
	@Override
	public AccountBalance getBalanceOfUser(String uid) {
		// TODO Auto-generated method stub
		return accountDao.getBalanceOfUser(uid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.AccountService#checkAccount(java.lang.String)
	 */
	@Override
	public String checkAccount(String uid) {
		// TODO Auto-generated method stub
		return accountDao.checkAccount(uid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.AccountService#inAccount(com.xunyanhui.model.TransactionRecord)
	 */
	@Override
	public int newTR(TransactionRecord tr) {
		// TODO Auto-generated method stub
		return accountDao.newTR(tr);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.AccountService#updateTRState(java.lang.String, int)
	 */
	@Override
	public int updateTRState(String trid, int state) {
		// TODO Auto-generated method stub
		return accountDao.updateTRState(trid, state);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.AccountService#bandCardOnAccount(com.xunyanhui.model.AccountCard)
	 */
	@Override
	public int bandCardOnAccount(AccountCard ac) {
		// TODO Auto-generated method stub
		return accountDao.bandCardOnAccount(ac);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.AccountService#unbandCardOnAccount(java.lang.String, java.lang.String)
	 */
	@Override
	public int unbandCardOnAccount(String uid, String cardno) {
		// TODO Auto-generated method stub
		return accountDao.unbandCardOnAccount(uid, cardno);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.AccountService#setMasterCardOfAccount(java.lang.String, java.lang.String)
	 */
	@Override
	public int setMasterCardOfAccount(String uid, String cardno) {
		// TODO Auto-generated method stub
		return accountDao.setMasterCardOfAccount(uid, cardno);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.AccountService#bandCardList(java.lang.String)
	 */
	@Override
	public List<AccountCardL> bandCardList(String uid) {
		// TODO Auto-generated method stub
		return accountDao.bandCardList(uid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.AccountService#checkCardBand(java.lang.String)
	 */
	@Override
	public AccountCard checkCardBand(String cardno) {
		// TODO Auto-generated method stub
		return accountDao.checkCardBand(cardno);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.AccountService#getBills(java.lang.String, long, long, int, int)
	 */
	@Override
	public List<Bill> getBills(String uid, String startTime, String endTime,
			int start, int length) {
		// TODO Auto-generated method stub
		return accountDao.getBills(uid, startTime, endTime, start, length);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.AccountService#updatePassWordOfAccount(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public int updatePassWordOfAccount(String uid, String password, String oldpd) {
		// TODO Auto-generated method stub
		return accountDao.updatePassWordOfAccount(uid, password, oldpd);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.AccountService#getMobile(java.lang.String)
	 */
	@Override
	public String getMobile(String uid) {
		// TODO Auto-generated method stub
		return accountDao.getMobile(uid);
	}
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.AccountService#checkAccountPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public String checkAccountPassword(String uid, String password) {
		// TODO Auto-generated method stub
		return accountDao.checkAccountPassword(uid, password);
	}

	@Override
	public Integer updateBalance(String uid, int value) {
		// TODO Auto-generated method stub
		return accountDao.updateBalance(uid, value);
	}

	@Override
	public AccountCard getCardInfo(String uid) {
		// TODO Auto-generated method stub
		return accountDao.getCardInfo(uid);
	}
	  
	

}
