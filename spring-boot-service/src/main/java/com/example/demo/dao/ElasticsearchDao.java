package com.example.demo.dao;

import com.example.demo.dao.utils.ESRestAPIUtils;

import java.io.IOException;

/**
 * @DESC: 操作recursive_log索引的DAO
 * @Auther: Anryg
 * @Date: 2020/11/25 15:34
 */
public class ElasticsearchDao {

    public static String aggSingleTerm(String index, String aggField) throws IOException {
        String aggQuery = String.format("{\n" +
                "  \"size\": 0,\n" +
                "  \"aggs\": {\n" +
                "    \"NAME\": {\n" +
                "      \"terms\": {\n" +
                "        \"field\": \"%s\",\n" +
                "        \"size\": 10\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}",aggField);
        String queryResult = ESRestAPIUtils.query(aggQuery, "POST", index + "/_search");
        return queryResult;
    }

}
