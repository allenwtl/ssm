package com.allenwtl.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EmailUtils {

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String getEmailOwner(String email) {
        return StringUtils.substringBetween(email, "@", ".");
    }

    public static String closedEmail(String email) {
		if(isValidEmail(email)){
            StringBuffer sb = new StringBuffer();
            int length = email.substring(0, email.indexOf("@")).length() ;
            sb.append(email.substring(0,2)).append("*****").append(email.substring( email.indexOf("@"), email.length()));
            return sb.toString();
        }
        return null ;
    }

}
