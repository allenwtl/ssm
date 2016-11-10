package com.allenwtl.util;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 格式化工具类
 * @author lee
 *
 */
public class FormatUtils {
	
	private static ConcurrentMap<String, String> fieldNameKeyMap = new ConcurrentHashMap<>();
	
	private static DecimalFormat decimalFormat = new DecimalFormat("##,##0.00");
	
	//保留字段
	static
	{
		fieldNameKeyMap.put("userid", "user_id") ;
		fieldNameKeyMap.put("borrowid", "borrow_id") ;
		fieldNameKeyMap.put("addtime", "add_time") ;
	}

	/***
	 * 格式化金额
	 * @return
	 */
	public static String formatMoney(Object number) {
		return formatMoney(CommonUtils.parseDouble(number));
	}
	
	/***
	 * 格式化金额
	 * @return
	 */
	public static String formatMoney(double money){
		return decimalFormat.format(money);
	}
	
	public static void formatMoney(DecimalFormat formater, Map<String, Object> map){
		for(String key : map.keySet()) {
			Object value = map.get(key) ;
			double number = CommonUtils.parseDouble(value) ;
			String data = formater.format(number) ;
			map.put(key, data) ;
		}
	}
	

	/**
	 * 将JavaBean的字段驼峰字段按照下划线分隔的方式转化为Map
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> bean2Map(Object bean) {
		Map<String, Field> fields = ReflectUtils.getFields(bean.getClass()) ;
		Map<String, Object> result = CommonUtils.stableMap(fields.size()) ;
		for(Field field : fields.values())
		{
			Object value = BeanAccessor.getValue(bean, field) ;
			if(value!=null)
			{
				String name = field.getName() ;
				String key = fieldNameKeyMap.get(name) ;
				if(key==null)
				{
					key = name2Key(name) ;
					fieldNameKeyMap.put(name, key) ;
				}
				result.put(key, value) ;
			}
		}
		return result ;
	}
	
	private static String name2Key(String name) {
		char[] array = name.toCharArray() ;
		StringBuilder builder = new StringBuilder() ;
		for(int index=0; index<array.length; index++) {
			char c = array[index] ;
			if (c >= 'A' && c <= 'Z') {
				c += 32 ;
				builder.append('_') ;
			}
			builder.append(c) ;
		}
		return builder.toString() ;
	}
	
	/**
	 * 遮蔽手机号码
	 * @param phone
	 * @return
	 */
	public static String coverPhone(String phone){
		if(phone!=null&&phone.length()>7) {
			String head = phone.substring(0, 3) ;
			int tag = phone.length()-4 ;
			String tail = phone.substring(tag, phone.length()) ;
			phone = appendStart(head, phone.length()-7) + tail ;
		} else if(phone.length()>3){
			String head = phone.substring(0, 3) ;
			phone = appendStart(head, phone.length()-3) ;
		}

		return phone;
	}
	
	/**
	 * 遮蔽邮箱
	 * @param email
	 * @return
	 */
	public static String coverEmail(String email){
		if(email==null||email.length()<3) return CommonUtils.emptyIfNull(email);
		email=appendStart(email.substring(0, 1), 3) + (email.indexOf("@")>-1 ? email.substring(email.lastIndexOf("@"),email.length()) : email.substring(email.length()-2,email.length()));
		return email;
	}
	
	
	/**
	 * 检测邮箱地址是否合法
	 * 
	 * @param email
	 * @return true合法 false不合法
	 */
	public static boolean isEmail(String email) {
		if (null == email || "".equals(email))
			return false;
		// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
		Pattern p = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// 复杂匹配
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	/**
	 * 字符串拼接多个*号
	 * @param input
	 * @param num
	 * @return
	 */
	public static String appendStart(String input, int num) {
		if(input!=null && num>0) {
			for(int i=0; i<num; i++) {
				input +="*" ;
			}
		}
		return input ;
	}
	
	//replace filed and limit
	public static String converCountSql(String sql){
		sql=sql.trim();
        sql=sql.toLowerCase();
        sql=sql.replaceAll("select.+from", "select count(1) from");
        sql=sql.replaceAll("limit.+", " ");
        
		return sql;
	}
	
	public static void main(String[] args) {
		System.out.println(converCountSql("select a.c,bc,e from table where aaa=33 limit 1,2;"));
	}
}
