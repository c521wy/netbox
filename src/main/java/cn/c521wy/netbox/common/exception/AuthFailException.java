package cn.c521wy.netbox.common.exception;

public class AuthFailException extends RuntimeException {
    public AuthFailException(String message) {
        super(message);
    }

    public AuthFailException(String message, Throwable cause) {
        super(message, cause);
    }
}