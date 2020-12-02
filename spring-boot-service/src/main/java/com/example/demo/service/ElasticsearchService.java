package com.example.demo.service;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * @DESC: ES查询service
 * @Auther: Anryg
 * @Date: 2020/9/14 16:05
 */
public interface ElasticsearchService {

    String simpleQuery(String index,String queryJson) throws IOException;

    String aggQuery(@NotNull String index, @NotNull String aggField) throws IOException;
}
