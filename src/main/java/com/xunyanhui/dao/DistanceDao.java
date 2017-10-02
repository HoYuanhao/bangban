package com.xunyanhui.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xunyanhui.bean.Distance;

public interface DistanceDao {
/**
 * 通过用用户ID查找经纬度
 * @param id
 * @return
 */
	public List<Distance> getUserLatAndLngById(@Param("id")String id);
	
	/**
	 * 通过帮办ID查找发布帮办时的经纬度
	 * @param id
	 * @return
	 */
	public List<Distance> getBangbanLatAndLngById(@Param("id")String id);
}
