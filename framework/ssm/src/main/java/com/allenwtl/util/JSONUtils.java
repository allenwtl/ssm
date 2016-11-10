package com.allenwtl.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON转换工具类
 */
public class JSONUtils {
	
	/**
	 *  指定JSON序列化的日期格式
	 *  String text = JSON.toJSONString(new Date(), dateSerialize("yyyy-MM-dd"));
	 * @param format
	 * @return
	 */
	public static SerializeConfig dateSerialize(String format){
		SerializeConfig mapping = new SerializeConfig();
        mapping.put(Date.class, new SimpleDateFormatSerializer(format));
        
        return mapping;
	}
	
	/**
	 * 将对象转化成MAP格式
	 * 
	 * @param object
	 * @return
	 */
	public static Map<String, Object> object2Map(Object object) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if(object == null) {
			return returnMap;
		}
		
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(object.getClass());
		} catch (IntrospectionException e1) {
			return returnMap;
		}

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			try {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(object, new Object[0]);
					if (result != null) {
						returnMap.put(propertyName, result);
					}
				}
			} catch (NullPointerException e) {
				continue;
			} catch (Exception e) {
				break;
			}
		}

		return returnMap;
	}
	
	/**
	 * 将对象转化成MAP格式
	 * 如果对象中 属性类型为String 值为("")  则去掉该属性
	 * @param object
	 * @return
	 */
	public static Map<String, Object> object2MapSpecail(Object object) {
		Map<String, Object> returnMap = new HashMap<String, Object>();

		if (object == null) {
			return returnMap;
		}

		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(object.getClass());
		} catch (IntrospectionException e1) {
			return returnMap;
		}

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			try {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(object, new Object[0]);
					if (result != null && !result.equals("")) {
						returnMap.put(propertyName, result);
					}
				}
			} catch (NullPointerException e) {
				continue;
			} catch (Exception e) {
				break;
			}
		}

		return returnMap;
	}
	
	/**
	 *  将对象转化成字符串，并格式化日期对象
	 * @param object
	 * @param dateFormat
	 * @return
	 */
	public static String toJSONString(Object object,String dateFormat) {
		return JSON.toJSONString(object, dateSerialize(dateFormat));
    }
}
