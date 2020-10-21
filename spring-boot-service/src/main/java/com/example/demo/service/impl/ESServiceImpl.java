package com.example.demo.service.impl;

import com.example.demo.service.IESService;
import com.example.demo.utils.ESRestAPIUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @DESC:
 * @Auther: Anryg
 * @Date: 2020/9/14 16:06
 */
@Service
public class ESServiceImpl implements IESService {


    @Override
    public String simpleQuery(String index, String queryJson) throws IOException {
        queryJson = String.format("{\n" +
                "  \"query\": {\n" +
                "    \"term\": {\n" +
                "      \"client_ip\": {\n" +
                "        \"value\": \"172.20.51.211\"\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}");
        return ESRestAPIUtils.searchByQueryJson(index,queryJson).toJSONString();
    }
}
