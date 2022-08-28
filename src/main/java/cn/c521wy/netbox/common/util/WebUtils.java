package cn.c521wy.netbox.common.util;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

    public static String getRemoteIp(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

}
