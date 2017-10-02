/**
 * 创建日期：2017-1-11下午3:55:04
 * 修改日期：
 * 作者：邢传军
 * 账户管理Dao
 */
package com.xunyanhui.dao;

import org.apache.ibatis.annotations.Param;

import com.xunyanhui.model.Complain;


public interface ComplainDao {
	
	
	/**
	 * 新建用户投诉
	 * @param complain
	 * @return
	 */
	public int newComplain(Complain complain);
	/**
	 * 获取申诉的详细内容
	 * @param performanceid			演艺活动id
	 * @param artistid				艺人id
	 * @return
	 */
	public Complain getComplain(	
			@Param("artistid") String artistid,
			@Param("performanceid") String performanceid);
	
	

}
