package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.aop.annotation.LogAnnotation;
import com.example.demo.config.ConfigFactory;
import com.example.demo.config.ElasticsearchProperty;
import com.example.demo.model.StatisticsReturnEntity;
import com.example.demo.utils.ReturnResponseUtils;
import com.example.demo.service.IESService;
import com.example.demo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @DESC:
 * @Auther: Anryg
 * @Date: 2020/9/7 19:53
 */
@RestController
@Slf4j
@RequestMapping(path = {"/test","/dev"})
//@ConfigurationProperties(value = "config2") 可以不用
@RefreshScope/**配置修改热生效*/
//@Profile()
public class TestController {

    @Value(value = "${kafka.hosts}")
    private String kafka;
    
    @Autowired
    TestService service;

    @Autowired
    private ElasticsearchProperty esProperty;

    @Autowired
    IESService esService;

    @LogAnnotation(desc = "调用fun1...")
    @RequestMapping(path = "/fun1")
    public StatisticsReturnEntity fun1(@RequestBody String str){
        StatisticsReturnEntity result;
        try {
            throw new Exception("手动抛异常...");
        } catch (Exception e) {
            result = ReturnResponseUtils.getErrorReturn(e);
            log.error(e.getMessage(),e);
        }
        return result;
    }

    @PostMapping(path = "/fun2")
    public String fun2(){
        return service.service("xyz");
    }

    @LogAnnotation(desc = "调用fun3")
    @RequestMapping(path = "/fun3")
    public Object fun3(){
        return ConfigFactory.getBean("esPort");
    }

    @LogAnnotation(desc = "调用fun4")
    @RequestMapping(path = "/fun4")
    public Object fun4(@RequestBody String index) throws IOException {
        StatisticsReturnEntity result;
        String resultJson = esService.simpleQuery(index, "");
        result = ReturnResponseUtils.getSuccessReturn(JSON.parseArray(resultJson));
        return result;
    }

    @RequestMapping(path = "/fun5")
    public String fun5(){
        Object result = ConfigFactory.getBean("xxxx");
        log.info("This is fun5..." + result);
        return result.toString();
    }

    @LogAnnotation(desc = "调用fun6...")
    @RequestMapping(path = "/fun6")
    public String fun6(){
        return esProperty.getName();
    }

    @LogAnnotation(desc = "调用fun7...")
    @RequestMapping(path = "/fun7")
    public String fun7(@RequestParam String str1, @RequestParam String str2){
        return str1 + str2;
    }


}
