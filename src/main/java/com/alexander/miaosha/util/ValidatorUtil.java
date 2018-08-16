package com.alexander.miaosha.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src) {
        if(StringUtils.isEmpty(src)) {
            return false;
        }
//        System.out.println(src);
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }

//	public static void main(String[] args) {
//			System.out.println(isMobile("18912341234"));
//			System.out.println(isMobile("1891234123"));
//	}
}
