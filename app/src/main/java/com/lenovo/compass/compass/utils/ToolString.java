package com.lenovo.compass.compass.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author 曾繁添
 * 
 */
public class ToolString {

	/**
	 * 获取UUID
	 * 
	 * @return 32UUID小写字符串
	 */
	public static String gainUUID() {
		String strUUID = UUID.randomUUID().toString();
		strUUID = strUUID.replaceAll("-", "").toLowerCase();
		return strUUID;
	}

	/**
	 * 判断字符串是否非空非null
	 * 
	 * @param strParm
	 *            需要判断的字符串
	 * @return 真假
	 */
	public static boolean isNoBlankAndNoNull(String strParm) {
		return !((strParm == null) || (strParm.equals("")));
	}

	/**
	 * 将流转成字符串
	 * 
	 * @param is
	 *            输入流
	 * @return
	 * @throws Exception
	 */
	public static String convertStreamToString(InputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append("\n");
		}
		return sb.toString();
	}

	/**
	 * 将文件转成字符串
	 * 
	 * @param file
	 *            文件
	 * @return
	 * @throws Exception
	 */
	public static String getStringFromFile(File file) throws Exception {
		FileInputStream fin = new FileInputStream(file);
		String ret = convertStreamToString(fin);
		// Make sure you close all streams.
		fin.close();
		return ret;
	}

	/**
	 * 字符全角化
	 * 
	 * @param input
	 * @return
	 */
	public static String ToSBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * 判断是否为手机号的正则表达式
	 *
	 * @param mobiles
	 *            传进来的字符串
	 * @return true为正确手机号，false不是手机号
	 */
	public static boolean isMobileNum(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 判断是否为密码
	 * @param password 必须为6—16位的数字字母组合
	 * @return
	 */
	public static boolean isPassword(String password) {
		Pattern p = Pattern.compile("^(?=.*?[a-zA-Z])(?=.*?[0-9])[a-zA-Z0-9]{6,16}$");
		Matcher m = p.matcher(password);
		return m.matches();
	}
}
