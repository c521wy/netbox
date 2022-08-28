package cn.c521wy.netbox.common.controller;

import cn.c521wy.netbox.common.exception.AuthFailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class BaseControllerAdvice {

    @ExceptionHandler(AuthFailException.class)
    @ResponseBody
    public ResponseEntity<?> authFailExceptionHandler(AuthFailException e) {
        log.error("", e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> exceptionHandler(Exception e) {
        log.error("", e);
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

}
