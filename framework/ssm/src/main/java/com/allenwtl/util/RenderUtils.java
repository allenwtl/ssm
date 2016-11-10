package com.allenwtl.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public final class RenderUtils {
	private static transient final Logger logger = LoggerFactory.getLogger(RenderUtils.class);
	
	private static ObjectMapper mapper = new ObjectMapper();

	private RenderUtils() {
	}

	public static String toJson(Object value) {
		try {
			return mapper.writeValueAsString(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T toObject(String json, Class<T> valueType) {

//
//		Assert.hasText(json);
//		Assert.notNull(valueType);
		try {
			return mapper.readValue(json, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T toObject(String json, TypeReference<?> typeReference) {
//		Assert.hasText(json);
//		Assert.notNull(typeReference);
		try {
			return mapper.readValue(json, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T toObject(String json, JavaType javaType) {
//		Assert.hasText(json);
//		Assert.notNull(javaType);
		try {
			return mapper.readValue(json, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void writeValue(Writer writer, Object value) {
		try {
			mapper.writeValue(writer, value);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 直接输出字符串.
	 */
	public static void renderText(HttpServletResponse response, Object text) {
		render(response, text, "text/plain;charset=UTF-8");
	}

	/**
	 * 直接输出XML.
	 */
	public static void renderHtml(HttpServletResponse response, Object html) {
		render(response, html, "text/html;charset=UTF-8");
	}

	/**
	 * 直接输出XML.
	 */
	public static void renderXML(HttpServletResponse response, Object xml) {
		render(response, xml, "text/xml;charset=UTF-8");
	}

	public static void render(HttpServletResponse response, Object obj, String contentType) {
		PrintWriter printWriter = null;
		String jsonStr = JSON.toJSONString(obj, SerializerFeature.WriteNullListAsEmpty,
				SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.QuoteFieldNames);
		try {
		    if(StringUtils.isBlank(contentType)){
		        contentType = "text/html;charset=UTF-8";
		    }
			response.setContentType(contentType);
			printWriter = response.getWriter();
			printWriter.write(jsonStr);
			printWriter.flush();
		} catch (IOException e) {
			logger.error("写JSON数据时发生异常[{}]", e);
		} finally {
			if (printWriter != null)
				printWriter.close();
			printWriter = null;
		}
	}

}