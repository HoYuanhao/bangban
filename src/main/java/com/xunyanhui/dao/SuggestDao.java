/**
 * 创建日期：2017-1-11下午3:55:04
 * 修改日期：
 * 作者：邢传军
 * 账户管理Dao
 */
package com.xunyanhui.dao;


import com.xunyanhui.bean.Suggest;


public interface SuggestDao {
	
	
	/**
	 * 新建用户建议与反馈
	 * @param complain
	 * @return
	 */
	public int newSuggest(Suggest suggest);

	

}
