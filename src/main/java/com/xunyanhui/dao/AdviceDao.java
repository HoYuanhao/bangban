/**
 * 创建日期：2017-3-3下午3:55:04
 * 修改日期：
 * 作者：邢传军
 * 建议与反馈Dao
 */
package com.xunyanhui.dao;

import com.xunyanhui.model.Advice;

public interface AdviceDao {
	
	
	/**
	 * 新建用户建议与反馈
	 * @param advice
	 * @return
	 */
	public int newAdvice(Advice advice);
	
	

}
