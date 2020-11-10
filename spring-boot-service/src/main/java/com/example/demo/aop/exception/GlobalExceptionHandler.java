package com.example.demo.aop.exception;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.model.StatisticsReturnEntity;
import com.example.demo.model.ReturnResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @DESC: 统一处理controller层除了请求路径错误、请求参数不合法之外的异常
 * 比如：规定的要用POST请求，而请求用的GET方式，该异常会在这里被捕获或者服务器请求内部异常等
 * @Auther: Anryg
 * @Date: 2020/9/10 15:02
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * @DESC: 全局默认异常处理方法，即在所有的service层、controller层的异常都最好不要进行try-catch处理，否则这里将获取不到任何错误信息
     * @return : 返回统一的错误对象
     * */
    @ExceptionHandler(value = Throwable.class)
    public StatisticsReturnEntity<JSONArray> defaultExceptionHandler(HttpServletRequest request, Exception e){
        StringBuffer requestURL = request.getRequestURL();
        log.error("当前请求URL报错...{}",requestURL,e);
        return ReturnResponseUtils.getErrorReturn(e);
    }
}
