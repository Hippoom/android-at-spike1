package com.deppon.app.june.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (UnsupportedEncodingException e) {
			Logger.e(e);
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (UnsupportedEncodingException e) {
			Logger.e(e);
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (UnsupportedEncodingException e) {
			Logger.e(e);
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (UnsupportedEncodingException e) {
			Logger.e(e);
		}
		return "";
	}

	public static String detectRegex(char ch) {
		String regex = "";
		boolean flag = false;
		Pattern pattern = Pattern.compile("\\d");
		Matcher matcher = pattern.matcher(String.valueOf(ch));
		if (!flag) {
			while (matcher.find()) {
				regex = "\\d*";
				flag = true;
			}
		}
		if (!flag) {
			pattern = Pattern.compile("[\u4e00-\u9fa5]");
			matcher = pattern.matcher(String.valueOf(ch));
			while (matcher.find()) {
				regex = "[\u4e00-\u9fa5]*";
				flag = true;
			}
		}
		if (!flag) {
			pattern = Pattern.compile("[A-Za-z]");
			matcher = pattern.matcher(String.valueOf(ch));
			while (matcher.find()) {
				regex = "[A-Za-z]*";
				flag = true;
			}
		}
		if (!flag) {
			pattern = Pattern.compile("[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]");
			matcher = pattern.matcher(String.valueOf(ch));
			while (matcher.find()) {
				regex = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]*";
				flag = true;
			}
		}

		return regex;
	}
}