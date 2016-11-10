package com.allenwtl.util;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpSSLUtil {


	/**
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String mapToUrl(Map<String, String> params, String encode) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (String key : params.keySet()) {
			String value = params.get(key);
			if (isFirst) {
				sb.append(key + "=" + URLEncoder.encode(value, encode));
				isFirst = false;
			} else {
				if (value != null) {
					sb.append("&" + key + "=" + URLEncoder.encode(value, encode));
				} else {
					sb.append("&" + key + "=");
				}
			}
		}
		return sb.toString();
	}
	
	public static String mapToUrlNoEncode(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		boolean isFirst = true;
		for (String key : params.keySet()) {
			String value = params.get(key);
			if (isFirst) {
				if(value.contains("\"")) {
					String str = value.replace("\"", "\\\"");
					sb.append(key + "=\"" + str + "\"");
				}else{
					sb.append(key + "=\"" + value + "\"");
				}
				
				isFirst = false;
			} else {
				if (value != null) {
					if(value.contains("\"")) {
						String str = value.replace("\"", "\\\"");
						sb.append("&" + key + "=\"" + str + "\"");
					}else{
						sb.append("&" + key + "=\"" + value + "\"");
					}
					
				} else {
					sb.append("&" + key + "=");
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 对map的value进行urlEncode
	 * @param inputMap
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, String> mapValueURLencode(Map<String, String> inputMap,String encode) throws UnsupportedEncodingException{
		Map<String, String> returnMap = new ConcurrentHashMap();
		for (Entry<String, String> en : inputMap.entrySet()) {
			if (en.getKey().equals("gateWay")) {
				returnMap.put(en.getKey(), en.getValue());
			}else {
				returnMap.put(en.getKey(), URLEncoder.encode(en.getValue(),encode));
				
			}
		}
		return returnMap;
	}
	
	/**
	 * 对map的value进行urlEncode
	 * @param inputMap
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, String> mapValueURLdecode(Map<String, String> inputMap,String encode) throws UnsupportedEncodingException{
		Map<String, String> returnMap = new ConcurrentHashMap();
		for (Entry<String, String> en : inputMap.entrySet()) {
			if (en.getKey().equals("gateWay")) {
				returnMap.put(en.getKey(), en.getValue());
			}else {
				returnMap.put(en.getKey(), URLDecoder.decode(en.getValue(),encode));
				
			}
		}
		return returnMap;
	}
	
	public static String mapToUrlSortByKeyAscciAsc(Map<String, String> inputMap){
		if (inputMap == null || inputMap.isEmpty()) {
			return null;
		}
		ArrayList<String> keys = new ArrayList<String>(inputMap.keySet());
		Collections.sort(keys);
		StringBuffer sbf = new StringBuffer();
		for (String key : keys) {
			sbf.append(key);
			sbf.append("=");
			sbf.append(inputMap.get(key));
			sbf.append("&");
		}
		sbf.setLength(sbf.length() - 1);
		String sourceStr = sbf.toString();
		return sourceStr;
	}
	
	public static Map<String, String> urlToMapBysplitStr(String url,String split){
		if (StringUtils.isBlank(url)) {
			return null;
		}
		Map<String, String> returnMap = new HashMap<String, String>();
		String[] str = url.split("\\"+split);
		String[] keyValues = null;
		for (String keyValue : str) {
			keyValues = keyValue.split("\\=");
			returnMap.put(keyValues[0].trim(), keyValues.length == 1?"":keyValues[1].trim());
		}
		return returnMap;
	}
	
	public static String urlEncode(String url) throws UnsupportedEncodingException{
    	Pattern pattern = Pattern.compile("(?<=\\=)([^\\&]+)");
    	Matcher matcher = pattern.matcher(url);
    	String findStr = "";
    	while (matcher.find()) {
    		findStr = matcher.group();
    		url = url.replace(findStr, URLEncoder.encode(findStr, "utf-8"));
			
		}
    	return url;
    }
}
