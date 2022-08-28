package cn.c521wy.netbox.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetUtils {

    public static String resolveIp(String domain) throws UnknownHostException {
        InetAddress address = InetAddress.getByName(domain);
        return address.getHostAddress();
    }

}
