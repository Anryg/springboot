package com.example.demo.service.impl;

import com.example.demo.dao.ElasticsearchDao;
import com.example.demo.dao.es.DomainSearchRouteRepository;
import com.example.demo.service.ElasticsearchService;
import com.example.demo.dao.utils.ESRestAPIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * @DESC:
 * @Auther: Anryg
 * @Date: 2020/9/14 16:06
 */
@Service
@CacheConfig
public class ElasticsearchServiceImpl implements ElasticsearchService {

    @Autowired
    DomainSearchRouteRepository domainSearchRouteRepository;

    @Autowired
    ElasticsearchRestTemplate restTemplate;


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

    /**
     * @DESC:
     * */
    public String aggQuery(@NotNull String index, @NotNull String aggField) throws IOException {
        String aggResult = ElasticsearchDao.aggSingleTerm(index, aggField);
        return aggResult;
    }
}
