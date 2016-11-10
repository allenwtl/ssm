package com.allenwtl.util;


import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    public static final String UTF8 = "UTF-8";

    @SuppressWarnings("rawtypes")
    public static final List EMPTY_LIST = new ArrayList();

    @SuppressWarnings("rawtypes")
    public static final Map EMPTY_MAP = new HashMap();

    public static boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static boolean isEmptyContainNull(String input) {
        return input == null || input.trim().isEmpty() || input.trim().equalsIgnoreCase("null");
    }

    public static String emptyIfNull(String input) {
        return input == null ? "" : input.trim();
    }

    public static String emptyIfNull(Object input) {
        return input == null ? "" : input.toString().trim();
    }

    public static String emptyIfNull(String input, String def) {
        input = emptyIfNull(input);
        return input.isEmpty() ? def : input;
    }

    public static int parseInt(Object data) {
        return parseInt(data, 0);
    }

    public static short parseShort(Object data) {
        if (data != null) {
            try {
                return (data instanceof Short) ? ((Short) data).shortValue() : Short.valueOf(String.valueOf(data));
            } catch (Exception e) {
            }
            ;
        }
        return 0;
    }

    public static int parseInt(Object data, int def) {
        if (data != null) {
            try {
                return (data instanceof Integer) ? ((Integer) data).intValue() : Integer.valueOf(String.valueOf(data));
            } catch (Exception e) {
            }
            ;
        }
        return def;
    }


    public static long parseLong(Object data, long def) {
        if (data != null) {
            try {
                return (data instanceof Long) ? ((Long) data).longValue() : Long.valueOf(String.valueOf(data));
            } catch (Exception e) {
            }
            ;
        }
        return def;
    }

    public static double parseDouble(Object data) {
        return parseDouble(data, (double) 0);
    }

    public static double parseDouble(Object data, double def) {
        if (data != null) {
            try {
                double value = def;
                if (data != null) {
                    if (data instanceof BigDecimal) {
                        value = ((BigDecimal) data).doubleValue();
                    } else if (data instanceof Double) {
                        value = ((Double) data).doubleValue();
                    } else {
                        value = Double.valueOf(String.valueOf(data));
                    }
                }
                return value == 0 ? 0 : MathUtils.roundHalfUp(value);
            } catch (Exception e) {
            }
        }
        return def;
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static int sizeOfList(List<?> list) {
        return list == null ? 0 : list.size();
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> emptyList() {
        return (List<T>) EMPTY_LIST;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> emptyList(List<T> list) {
        return (List<T>) (list == null ? emptyList() : list);
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> emptyMap() {
        return (Map<K, V>) EMPTY_MAP;
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> emptyMap(Map<K, V> map) {
        return map == null ? EMPTY_MAP : map;
    }

    public static <K, V> Map<K, V> stableMap(int size) {
        return new HashMap<K, V>(size, 1.0f);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getValue(Map<String, ?> dataMap, String key) {
        Object value = dataMap.get(key);
        return value == null ? null : (T) value;
    }

    /**
     * 获取数组指定位置的值,越界则返回def
     *
     * @param array
     * @param index
     * @param def
     * @return
     */
    public static <T> T indexGet(T[] array, int index, T def) {
        int arrayLength = array == null ? 0 : array.length;
        return indexGet(array, arrayLength, index, def);
    }

    /**
     * 获取数组指定位置的值,越界则返回def
     *
     * @param array
     * @param arrayLength
     * @param index
     * @param def
     * @return
     */
    public static <T> T indexGet(T[] array, int arrayLength, int index, T def) {
        if (index >= 0 && index < arrayLength) {
            return array[index];
        }
        return def;
    }

    public static IllegalStateException illegalStateException(Throwable t) {
        return new IllegalStateException(t);
    }

    public static IllegalStateException illegalStateException(String message) {
        return new IllegalStateException(message);
    }

    public static IllegalStateException illegalStateException(String message, Throwable t) {
        return new IllegalStateException(message, t);
    }

    public static IllegalArgumentException illegalArgumentException(String message) {
        return new IllegalArgumentException(message);
    }

    public static UnsupportedOperationException unsupportedMethodException() {
        return new UnsupportedOperationException("unsupport this method");
    }


    public static Throwable foundRealThrowable(Throwable t) {
        Throwable cause = t.getCause();
        if (cause == null) return t;
        return foundRealThrowable(cause);
    }

    /**
     * 格式化异常
     *
     * @param t
     * @return
     */
    public static String formatThrowable(Throwable t) {
        if (t == null) return "";
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }

    public static String formatThrowableForHtml(Throwable t) {
        String ex = formatThrowable(t);
        return ex.replaceAll("\n\t", " ");
    }

    /**
     * 实例化对象,注意该对象必须有无参构造函数
     *
     * @param klass
     * @return
     */
    public static <T> T newInstance(Class<T> klass) {
        try {
            return (T) klass.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("instance class[" + klass + "] with ex:", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String className) {
        try {
            return (T) newInstance(Class.forName(className));
        } catch (Exception e) {
            throw new IllegalArgumentException("instance class[" + className + "] with ex:", e);
        }
    }

    public static Class<?> classForName(String className) {
        try {
            return Class.forName(className);
        } catch (Exception e) {
            throw new IllegalArgumentException("classForName(" + className + ")  with ex:", e);
        }
    }

    public static String urlDecodeUTF8(String input) {
        if (input == null)
            return null;
        try {
            return URLDecoder.decode(input, UTF8);
        } catch (Exception e) {
            throw illegalStateException(e);
        }
    }

    public static String urlEncodeUTF8(String input) {
        if (input == null)
            return null;
        try {
            return URLEncoder.encode(input, UTF8);
        } catch (Exception e) {
            throw illegalStateException(e);
        }
    }

    public static <K, V> void putIfNotNull(Map<K, V> map, K key, V value) {
        if (map != null && key != null && value != null) map.put(key, value);
    }

    public static InputStream getInputStreamFromClassPath(String filename) {
        return CommonUtils.isEmpty(filename) ? null : CommonUtils.class.getClassLoader().getResourceAsStream(filename);
    }

    /**
     * 过滤html标签和部分特殊字符
     *
     * @param content
     * @return
     */
    public static String filterHtml(String content) {
        if (CommonUtils.isEmpty(content)) return "";

        Map<String, String> regs = new HashMap<String, String>();
        regs.put("<([^>]*)>", "");
        regs.put("(&nbsp;)", " ");
        regs.put("(&apos;)", "'");
        regs.put("(&quot;)", "\"");
        regs.put("(&ldquo;)", "“");
        regs.put("(&rdquo;)", "”");
        regs.put("(&lt;)", "<");
        regs.put("(&gt;)", ">");
        regs.put("(&ndash;)", "-");

        Pattern p = null;
        Matcher m = null;
        for (Map.Entry<String, String> entry : regs.entrySet()) {
            p = Pattern.compile(entry.getKey(), Pattern.CASE_INSENSITIVE); //横杠
            m = p.matcher(content);
            content = m.replaceAll(entry.getValue());
        }

        return content;
    }

    /**
     * 替换HTML中的标签字符
     *
     * @param source
     * @return
     */
    public static String htmlEncode(String source) {
        if (source == null) {
            return "";
        }
        String html = "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
                case '<':
                    buffer.append("&lt;");
                    break;
                case '>':
                    buffer.append("&gt;");
                    break;
                case '&':
                    buffer.append("&amp;");
                    break;
                case '"':
                    buffer.append("&quot;");
                    break;
                case '\'':
                    buffer.append("&apos;");
                    break;

//            case 10:
//            case 13:
//                break;
                default:
                    buffer.append(c);
            }
        }
        html = buffer.toString();
        return html;
    }


    /**
     * 兼容旧数据
     *
     * @param str
     * @return
     */
    @Deprecated
    public static String replaceHref(String str) {
        if (str == null) return null;
        String firstStr = "<a href='/invest/a", lastStr = ".html' target=_blank>";
        String firstReplace = "<a href='/invest-page.html?id=", lastReplace = "' target=_blank>";
        int first = str.indexOf(firstStr);
        if (first >= 0) {
            str = str.replaceAll(firstStr, firstReplace);
            str = str.replaceAll(lastStr, lastReplace);
        }
        return str;
    }

    public static int size(Collection<?> c) {
        return c == null ? 0 : c.size();
    }

    public static String getSensitiveWords(String content, List<String> sensitiveList) {
        List<String> sens = new ArrayList<>();
        for (String string : sensitiveList) {
            if (StringUtils.contains(content, string)) {
                sens.add(string);
            }
        }
        return sens.size() > 0 ? StringUtils.join(sens, ",") : null;
    }


    public static String converListToString(List<String> topicIdList) {
        StringBuilder topicIdSb = new StringBuilder();
        for (String topicId : topicIdList) {
            topicIdSb.append("\"").append(topicId).append("\"").append(",");
        }
        return topicIdSb.toString().substring(0, topicIdSb.toString().length() - 1);
    }
}
