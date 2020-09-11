package com.example.demo.aop.exception;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @DESC: 统一处理controller层的异常
 * @Auther: Anryg
 * @Date: 2020/9/10 15:02
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * @DESC: 全局默认异常处理方法
     * @return : 页面只返回异常消息，错误栈只在日志显示
     * */
    @ExceptionHandler(value = Throwable.class)
    public String defaultExceptionHandler(HttpServletRequest request, Exception e){
        StringBuffer requestURL = request.getRequestURL();
        log.error("当前请求URL报错...{}",requestURL,e);
        return e.getMessage();
    }
}
