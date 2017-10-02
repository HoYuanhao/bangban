/**
 * 创建日期：2017-1-11下午4:01:16
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.service;



import com.xunyanhui.bean.Suggest;




public interface SuggestService {

	/**
	 * 新建用户建议和反馈
	 * @param complain
	 * @return
	 */
	public int newSuggest(Suggest suggest);
	/**
	 * 获取申诉的详细内容
	 * @param performanceid			演艺活动id
	 * @param artistid				艺人id
	 * @return
	 
	public Complain getComplain(String artistid,String performanceid);
	*/
}
