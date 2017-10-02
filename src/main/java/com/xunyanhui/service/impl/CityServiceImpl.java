/**
 * 创建日期：2017-1-12下午7:35:54
 * 修改日期：
 * 作者：邢传军
 */
package com.xunyanhui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xunyanhui.dao.CityDao;
import com.xunyanhui.model.City;
import com.xunyanhui.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;
	/* (non-Javadoc)
	 * @see com.xunyanhui.service.CityService#getCityList()
	 */
	@Override
	public List<City> getCityList() {
		// TODO Auto-generated method stub
		return cityDao.getCityList();
	}

}
