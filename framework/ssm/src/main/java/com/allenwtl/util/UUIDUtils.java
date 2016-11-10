package com.allenwtl.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UUIDUtils {

	/**
	 * 返回21位数字型随机数
	 * 
	 * @return
	 */
	public static String getOrderNo() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return df.format(new Date()) + RandomStringUtils.randomNumeric(4);
	}

	/**
	 * 返回字符型唯一码
	 * 
	 * @return
	 */
	public static String getUuidStr() {
		return UUID.randomUUID().toString().replace("-", "");
	}


	public static String getRandomString(){
		return RandomStringUtils.random(8, true, false);
	}


	public static void main(String[] args) {
		System.out.println(getUuidStr());
	}

}
