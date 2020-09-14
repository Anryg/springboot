package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.aop.annotation.LogAnnotation;
import com.example.demo.entity.StatisticsReturnEntity;
import com.example.demo.response.ReturnResponse;
import com.example.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @DESC:
 * @Auther: Anryg
 * @Date: 2020/9/7 19:53
 */
@RestController
@Slf4j
@RequestMapping(path = {"/test","/dev"})
//@Profile()
public class TestController {
    
    @Autowired
    TestService service;

    @LogAnnotation(desc = "调用fun1...")
    @RequestMapping(path = "/fun1")
    public StatisticsReturnEntity fun1(@RequestBody String str){
        StatisticsReturnEntity result = ReturnResponse.getEmptyReturn(10);
        try {
            throw new Exception("手动抛异常...");
        } catch (Exception e) {
            result = ReturnResponse.getErrorReturn(e);
            log.error(e.getMessage(),e);
        }
        return result;
    }

    @PostMapping(path = "/fun2")
    public String fun2(){
        return service.service("xyz");
    }


}
