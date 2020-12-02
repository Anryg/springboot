package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.aop.annotation.LogAnnotation;
import com.example.demo.model.ReturnResponseUtils;
import com.example.demo.model.StatisticsReturnEntity;
import com.example.demo.service.ElasticsearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @DESC: 查询ES的controller入口
 * @Auther: Anryg
 * @Date: 2020/9/14 15:59
 */
@RestController
@Slf4j
@RequestMapping(path = "/es")
@CacheConfig(cacheNames = "commonESCache")
public class ElasticsearchController {

    @Autowired
    ElasticsearchService esService;

    @LogAnnotation(desc = "调用getSingleTerm...")
    @RequestMapping(path = "/singleTerm")
    @Cacheable(key = "#index + #field")
    public StatisticsReturnEntity getSingleTerm(@RequestParam String index, @RequestParam String field) throws IOException {
        String resultString = esService.aggQuery(index, field);
        JSONObject resultJson = JSON.parseObject(resultString);
        return ReturnResponseUtils.getSuccessReturn(resultJson);
    }


}
