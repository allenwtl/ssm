package com.allenwtl.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class MobileUtils {
	// 手机对应运营商标编号 YD = 1 移动; DX = 2 电信; LT = 3 联通
	public static final int YD = 1;
	public static final int DX = 2;
	public static final int LT = 3;
	public static final int QT = 0;
	public static List<String> YD_LIST;
	public static List<String> DX_LIST;
	public static List<String> LT_LIST;
	// 初始化手机号码验证正则表达式
	public static Pattern pattern;
	static {
		String yd_hao_duan = "134,135,136,137,138,139,150,151,152,157,158,159,187,188,182,183,147,184,178";
		String dx_hao_duan = "133,153,180,181,189,177";
		String lt_hao_duan = "130,131,132,154,145,155,156,185,186,170,176";
		// 初始化运营商手机号段列表
		YD_LIST = Arrays.asList(yd_hao_duan.split(","));
		DX_LIST = Arrays.asList(dx_hao_duan.split(","));
		LT_LIST = Arrays.asList(lt_hao_duan.split(","));
		String mobileno_check_regex = "^13[0-9]{9}$|14[0-9]{9}$|15[0-9]{9}$|17[0-9]{9}$|18[0-9]{9}$";
		pattern = Pattern.compile(mobileno_check_regex);
	}

	/**
	 * 根据号码前缀判断号码归属运营商 
	 * 中国移动: 182,187,188,139,138,137,136,135,134,147,159,158,157,152,151,150 
	 * 中国联通: 186,185,156,155,145,132,131,130 
	 * 中国电信: 133,1349,153,180,189
	 * 
	 * @param mobileNo
	 * @return
	 * @create_time 2011-12-30 下午05:23:39
	 */
	public static int mobileNoType(String mobileNo) {
		if (mobileNo.length() < 3) {
			return DX;
		}
		// 号码前3位
		String prefix3 = mobileNo.substring(0, 3);
		if (DX_LIST.contains(prefix3)) {
			return DX;
		}
		if (LT_LIST.contains(prefix3)) {
			return LT;
		}
		if (YD_LIST.contains(prefix3)) {
			return YD;
		}

		return QT;
	}
	
	/**
	 * 正则验证手机号码格式
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		if(StringUtils.isEmpty(mobile)) {
			return false;
		}
		return pattern.matcher(mobile.trim()).matches();
	}
	
    /**
     * 手机号码信息屏蔽
     * @param mobile
     * @return
     */
    public static String closedMobile(String mobile) {
    	if(StringUtils.isBlank(mobile) || mobile.length() < 8){
    		return mobile;
    	}
        return new StringBuffer(mobile.substring(0, mobile.length() - 4)).append("****").toString();
    }
}
