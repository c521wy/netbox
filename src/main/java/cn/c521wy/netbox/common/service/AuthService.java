package cn.c521wy.netbox.common.service;

import cn.c521wy.netbox.common.exception.AuthFailException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class AuthService {

    @Value("${auth.token}")
    private String authToken;

    public static final String HEADER_AUTH_TOKEN = "X-AUTH";


    public void checkToken(HttpServletRequest request) throws AuthFailException {

        if (StringUtils.isBlank(authToken)) {
            throw new AuthFailException("config error: auth.token must not be empty");
        }

        String authTokenValue = request.getHeader(HEADER_AUTH_TOKEN);

        if (!StringUtils.equals(authToken, authTokenValue)) {
            throw new AuthFailException("authToken not match");
        }

    }

}
