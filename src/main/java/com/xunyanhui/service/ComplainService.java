/**
 * 创建日期：2017-1-11下午4:01:16
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.service;

import com.xunyanhui.model.Complain;


public interface ComplainService {

	/**
	 * 新建用户申诉
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
	public Complain getComplain(String artistid,String performanceid);
}
