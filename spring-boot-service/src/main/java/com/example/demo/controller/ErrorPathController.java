package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.aop.annotation.LogAnnotation;
import com.example.demo.model.StatisticsReturnEntity;
import com.example.demo.utils.ReturnResponseUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @DESC: 处理所有请求了不存在路径的controller
 * @Auther: Anryg
 * @Date: 2020/9/23 09:35
 */
@RestController(value = "errorPathController")
public class ErrorPathController implements ErrorController {
    private final static String ERROR_PATH = "/error";

    @LogAnnotation(desc = "请求了不存在的路径")
    @RequestMapping(path = ERROR_PATH /**支持所有请求方式*/)
    public StatisticsReturnEntity<JSON> errorPathReturn(){
        return ReturnResponseUtils.getErrorPathReturn();
    }


    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
