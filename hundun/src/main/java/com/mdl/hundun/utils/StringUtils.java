package com.mdl.hundun.utils;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具 主要对 StringUtils 的一些方法进行重写,达到更方便的使用
 *
 * @ClassName: StringUtils
 * @Description
 * @author xiekun
 * @date 2017年11月1日 下午5:14:42
 *
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

	/**
	 * 格式化byte
	 *
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] out = new char[b.length * 2];
		for (int i = 0; i < b.length; i++) {
			byte c = b[i];
			out[i * 2] = digit[(c >>> 4) & 0X0F];
			out[i * 2 + 1] = digit[c & 0X0F];
		}

		return new String(out);
	}

	/**
	 * 换行符javajava去除字符串中的空格、回车、换行符、制表符
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|        |\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}


	/**
	 * 生成uuid
	 * @return
	 */
	public static String getUuid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}


	/*
	 * Function name: dealNull Description: \u8655理空字符串 Input: String str
	 * Output: 不等於null的String
	 */
	public static String dealNull(Object str) {
		String returnstr = null;
		if (str == null) {
			returnstr = "";
		} else {
			returnstr = str.toString();
		}
		return returnstr;
	}

	/**
	 * 数字转汉字
	 * @param num
	 * @return
	 */
	public static String convert(int num) {
		String[] nums = {"零","一","二","三","四","五","六","七","八","九"};
		String[] unit = {"","十","百","千","万","十","百","千","亿","十","百","千","万亿"};
		String str = String.valueOf(num);
		char[] charNum = str.toCharArray();
		String result = "";
		int length = str.length();
		for(int i = 0; i < length; i++) {
			int c = charNum[i] - '0';
			if(c != 0) {
				result += nums[c] + unit[length - i - 1];
			}else{
				result +=  nums[c];
			}
		}
		return result;
	}


	public static String[] getNullPropertyNames (Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<>();
		for(java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) {
				emptyNames.add(pd.getName());
			}
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	/**
	 * 复制不为空的字段的值
	 * @param src
	 * @param target
	 */
	public static void copyPropertiesIgnoreNull(Object src, Object target){
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}

	/**
	 * 简化字符串 超长用......代替 常用于避免过长日志输出
	 * @param str
	 * @param i
	 * @return
	 */
	public static String trimShort(String str, int i) {
		if(str == null){
			return "";
		}

		if(str.length() <= i){
			return str;
		}else{
			return str.substring(0,i).concat("......");
		}
	}

	public static String trimLog(String str) {
		return trimShort(str,100);
	}

}
