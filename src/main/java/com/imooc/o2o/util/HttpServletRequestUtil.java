package com.imooc.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {
    public static int getInt(HttpServletRequest request, String key) {
        try {
            return Integer.decode(request.getParameter(key));
        } catch (Exception ignore) {
            return -1;
        }
    }

    public static long getLong(HttpServletRequest request, String key) {
        try {
            return Long.parseLong(request.getParameter(key));
        } catch (Exception ignore) {
            return -1;
        }
    }

    public static Double getDouble(HttpServletRequest request, String key) {
        try {
            return Double.parseDouble(request.getParameter(key));
        } catch (Exception ignore) {
            return -1d;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key) {
        try {
            return Boolean.parseBoolean(request.getParameter(key));
        } catch (Exception ignore) {
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            if (result != null){
                result = result.trim();
            }
            if("".equals(result)){
                result = null;
            }
            return result;
        } catch (Exception ignore) {
            return null;
        }
    }
}
