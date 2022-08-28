package cn.c521wy.netbox.common.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

    public static String getRemoteIp(HttpServletRequest request) {

        String realIp = request.getHeader("X-Real-IP");

        if (StringUtils.isNotBlank(realIp)) {
            return realIp;
        }

        return request.getRemoteAddr();
    }

}
