package com.xunyanhui.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtil {

	static public boolean regEmail(String email) {
		String regEx = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
		boolean rs = regBase(email, regEx);
		return rs;

	}

	static public boolean regUserName(String userName) {

		String regEx = "^[A-Za-z][A-Za-z1-9_-]+$";
		boolean rs = regBase(userName, regEx);
		return rs;

	}

	static public boolean regPasswd(String passwd) {
		String regEx = "^[A-Za-z][A-Za-z1-9_-]+$";
		boolean rs = regBase(passwd, regEx);
		return rs;
	}

	static public boolean regMobile(String mobile) {
		String regEx = "^1[3|4|5|8][0-9]\\d{8}$";
		boolean rs = regBase(mobile, regEx);
		return rs;

	}

	static public boolean regBase(String str, String regEx) {
		Pattern pattern = Pattern.compile(regEx);
		// 忽略大小写的写法
		// Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		// 字符串是否与正则表达式相匹配
		boolean rs = matcher.matches();
		return rs;
	}

}
