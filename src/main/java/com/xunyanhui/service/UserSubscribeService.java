/**
 * 创建日期：2016-12-22下午5:31:47
 * 修改日期：
 * 作者：邢传军
 * 目的：用户订阅演艺活动情况
 */
package com.xunyanhui.service;
import java.util.List;


import com.xunyanhui.bean.Performancetype;
import com.xunyanhui.bean.UserSubscribeV;
import com.xunyanhui.bean.UserSubscribeVS;


public interface UserSubscribeService {
	/**
	 * 获取帮办类型
	 * @return Performancetype
	 */
public List<Performancetype> selectPerformancetype();
	/**
	 * 读取指定用户的演艺活动订阅情况
	 * @param uid
	 * @return
	 */
	public List<UserSubscribeV> selectUserSubscribeByUid(String uid);
	
	/**
	 * 修改某一指定用户的某一演艺活动的订阅情况
	 * @param uid
	 * @param pid
	 * @param substate
	 * @return
	 */
	public int updateUserSubscribeByUid( String uid,int pid, int substate);
	/**
	 * 读取订阅了指定演艺活动的用户列表
	 * @param pid
	 * @return
	 */
	public List<UserSubscribeVS> selectSubscribeByPid(int pid);
	/**
	 * 往用户帮办关注表中添加默认值
	 * @param uid
	 * @param pid
	 * @param subtime
	 */
	public boolean addSubscribeBypid(List<UserSubscribeV> usersublist);
}
