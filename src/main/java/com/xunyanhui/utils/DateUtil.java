/**
 * 创建日期：2017-1-13下午4:34:41
 * 修改日期：处理时间
 * 作者：邢传军
 */
package com.xunyanhui.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/*
	 * 判断local是否在start与end之间,时间类型
	 */
	public static boolean isInclude(Date start, Date end, Date local) {
		boolean ret = false;
		if (start.getTime() <= local.getTime()
				&& end.getTime() >= local.getTime()) {
			ret = true;
		}
		return ret;
	}

	/*
	 * 判断两组时间间隔之间是否包含
	 */
	public static boolean isIncludeOfTwo(String d1_begin, String d1_end,
			String d2_begin, String d2_end) {
		boolean ret = true;
		try {
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date d1_begin_d = format.parse(d1_begin);
			Date d1_end_d = format.parse(d1_end);
			Date d2_begin_d = format.parse(d2_begin);
			Date d2_end_d = format.parse(d2_end);
			if (d1_end_d.before(d2_begin_d) || d2_end_d.before(d1_begin_d)) {
				//时间段2在时间段1里面
				ret = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	/*
	 * 判断local是否在start与end之间,时间类型
	 * 
	 * public static boolean isInclude(String start,String end,String local){
	 * boolean ret = false; if (start.getTime() <=
	 * local.getTime()&&end.getTime() >= local.getTime()) { ret = true; } return
	 * ret; }
	 */
}
