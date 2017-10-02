/**
 * 创建日期：2016-12-14下午4:58:59
 * 修改日期：
 * 作者：邢传军
 * 目的：用于进行系统配置文件读写
 */
package com.xunyanhui.service;

public interface PropertiesService {

	
	/**
	 * 读取指定key的属性
	 * @param key
	 * @return
	 */
	public String getProperties(String key);
}
