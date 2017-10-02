/**
 * 创建日期：2016-12-17上午10:41:41
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.service;



public interface AuthFileService {

	/**
	 * 插如一个实名认证文件信息
	 *  id				文件id
	 *  type				文件类型，身份证；护照；驾驶证等
	 *  authname			认证实名
	 *  idnum				证件编号
	 *  filename			上传文件名
	 *  path				上传文件访问路径
	 *  uid				上传文件用户id(申请认证人id)
	 * @return
	 */
	public int insertAuthFile(String id, String type,String authname,String idnum,String filename, String path,String uid);


}
