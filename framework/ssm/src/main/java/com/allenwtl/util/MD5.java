package com.allenwtl.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MD5 {
	
	private static final Charset utf8 = Charset.forName("utf-8");

	public final static String encode1(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 恒朋MD5加密
	 * @param s
	 * @param encode
	 * @return
	 */
	public final static String HPEncode(String s,String encode) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] strTemp = s.getBytes(encode);
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
	
	public final static String md5Encode(String s) {
		return DigestUtils.md5Hex(s.getBytes(utf8));
	}

	public final static String encode(String s, String encode) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes(encode);
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * MD5标准计算摘要
	 * */
	public static String digest(String inbuf) {
		MessageDigest m;
		try {
	m = MessageDigest.getInstance("MD5");
			m.update(inbuf.getBytes(), 0, inbuf.length());
			return new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String [] arr){
	    String str="456014201103189145766020110318172833<body><issueNotify><issue gameName=\"ssl\" number=\"2011031815\" startTime=\"2011-03-18 16:58:00\" stopTime=\"2011-03-18 17:28:00\" status=\"3\" bonusCode=\"\" salesMoney=\"-1.0\" bonusMoney=\"-1.0\"/></issueNotify></body></message>";
        System.out.println(MD5.HPEncode(str,"GBK"));
	}
	
    public static String Md5Sign(Map<String, String> sourceMap, String privateKey) {
        if (sourceMap == null || sourceMap.isEmpty()) {
            return null;
        }
        List<String> keys = new ArrayList<String>(sourceMap.keySet());
        Collections.sort(keys);
        StringBuffer sbf = new StringBuffer(privateKey);
        for (String key : keys) {
        	if(StringUtils.isNotBlank(sourceMap.get(key))){
        		sbf.append(sourceMap.get(key));
        	}
        }
        String sourceStr = sbf.toString();
        return MD5.md5Encode(sourceStr);
    }
	
}
