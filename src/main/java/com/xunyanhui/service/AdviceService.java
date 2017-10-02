/**
 * 创建日期：2017-3-3下午4:01:16
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.service;


import com.xunyanhui.model.Advice;


public interface AdviceService {
	
	/**
	 * 新建用户建议与反馈
	 * @param advice
	 * @return
	 */
	public int newAdvice(Advice advice);
	
}
