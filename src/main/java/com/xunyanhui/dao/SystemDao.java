package com.xunyanhui.dao;

import java.util.List;

import com.xunyanhui.model.HomePic;

/**
 * 关于系统相关信息的Dao
 * 
 * @author 柯鑫
 * 
 */
public interface SystemDao {
	/**
	 * 获取首页图片数据
	 * 
	 * @return 返回首页图片列表
	 */
	public List<HomePic> getHomePicList();

}
