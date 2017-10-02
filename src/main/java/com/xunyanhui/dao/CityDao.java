/**
 * 创建日期：2017-1-12下午7:31:10
 * 修改日期：
 * 作者：邢传军
 * 操作城市
 */
package com.xunyanhui.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.xunyanhui.model.City;

public interface CityDao {
	
	/**
	 * 读取所用城市
	 * @return
	 */
	public List<City> getCityList();
	

}
