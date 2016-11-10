package com.allenwtl.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;


public final class IPUtils {
	
	/** 默认空值 */
	private static final String EMPTY = "-";
	//public static final Logger logger = LoggerFactory.getLogger(IPUtils.class);
	
	/**
	 * 得到访客真实IP地址
	 */
	public static String getRemoteTrueIp(HttpServletRequest request) {	
		//首先获得用户的代理服务
        String fromIp = request.getHeader("x-forwarded-for");
        //logger.info("获取到用户的原始的HTTP_X_FORWARDED_FOR:{}", fromIp);
        
        //再获取nginx的ip
        if (StringUtils.isBlank(fromIp) || "unknown".equals(fromIp.trim())) {
            fromIp = request.getHeader("X-Real-IP");
            //logger.info("获取到用户的原始的X-Real-IP:{}", fromIp);
        }
        
        //再获取用户的请求ip
        if (StringUtils.isBlank(fromIp)) {
            fromIp = request.getRemoteAddr();
            //logger.info("获取到用户的RemoteIP:{}", fromIp);
        }
        
        // 取最前面的IP地址
        String[] fromIpArr = fromIp.split(",");
        if (fromIpArr.length > 0) {
            fromIp = fromIpArr[0];
        }
        
        //logger.info("当前请求获取到用户的IP为:{}", fromIp);
        
        if (StringUtils.isBlank(fromIp))fromIp = EMPTY;
        
        return fromIp;
	}

	/**
	 * 得到访客真实IP地址
	 */
	public static String getRemoteTrueIpWithoutLog(HttpServletRequest request) {	
		//首先获得用户的代理服务
        String fromIp = request.getHeader("x-forwarded-for");
        //logger.info("获取到用户的原始的HTTP_X_FORWARDED_FOR:{}", fromIp);
        
        //再获取nginx的ip
        if (StringUtils.isBlank(fromIp) || "unknown".equals(fromIp.trim())) {
            fromIp = request.getHeader("X-Real-IP");
            //logger.info("获取到用户的原始的X-Real-IP:{}", fromIp);
        }
        
        //再获取用户的请求ip
        if (StringUtils.isBlank(fromIp)) {
            fromIp = request.getRemoteAddr();
            //logger.info("获取到用户的RemoteIP:{}", fromIp);
        }
        
        // 取最前面的IP地址
        String[] fromIpArr = fromIp.split(",");
        if (fromIpArr.length > 0) {
            fromIp = fromIpArr[0];
        }
        
        //logger.info("当前请求获取到用户的IP为:{}", fromIp);        
        if (StringUtils.isBlank(fromIp))fromIp = EMPTY;
        
        return fromIp;
	}
	
	
	/**
	 * 把IP地址转换成整型
	 * @param ipAddr IP地址
	 * @return
	 * @create_time 2011-9-28 下午02:06:47
	 */
    public static int getIpIntByStr(String ipAddr) {
        if(StringUtils.isBlank(ipAddr)||EMPTY.equals(ipAddr)){
            return 0;
        }
        StringTokenizer tokenizer = new StringTokenizer(ipAddr,".");
        int num = 0,i=-1;
        while (tokenizer.hasMoreElements()) {
            String value = (String) tokenizer.nextElement();
            int power = 3 -(++i);
            if(StringUtils.isNumeric(value)){
               num += ((Integer.valueOf(value)%256*Math.pow(256, power)));
            }
        }
        return num;
    }
    
}
