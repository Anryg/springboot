package com.example.demo.service;

import java.io.IOException;

/**
 * @DESC: ES查询service
 * @Auther: Anryg
 * @Date: 2020/9/14 16:05
 */
public interface IESService {

    String simpleQuery(String index,String queryJson) throws IOException;
}
