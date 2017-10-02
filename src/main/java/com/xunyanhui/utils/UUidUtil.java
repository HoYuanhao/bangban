package com.xunyanhui.utils;

import java.util.UUID;

public class UUidUtil {

	public static String getUUid() {
		UUID ran = UUID.randomUUID();
		String s = ran.toString();
		s = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
				+ s.substring(19, 23) + s.substring(24);
		return s;
	}

}
