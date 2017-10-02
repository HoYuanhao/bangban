/**
 * 创建日期：2016-12-17上午10:24:56
 * 修改日期：
 * 作者：邢传军
 * 目的：用于处理实名认证文件
 */
package com.xunyanhui.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;



public interface AuthFileDao {

	
	/**
	 * 插如一个实名认证文件信息
	 * @param id				文件id
	 * @param type				文件类型，身份证；护照；驾驶证等
	 * @param authname			认证实名
	 * @param idnum				证件编号
	 * @param filename			上传文件名
	 * @param path				上传文件访问路径
	 * @param uid				上传文件用户id(申请认证人id)
	 * @return
	 */
	public int insertAuthFile(
			@Param("id") String id,
			@Param("type") String type,
			@Param("authname") String authname,
			@Param("idnum") String idnum,
			@Param("filename") String filename,
			@Param("path") String path,
			@Param("uid") String uid
			);
}
