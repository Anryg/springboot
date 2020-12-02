package com.example.demo.dao.config;

import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @DESC: Elasticsearch的配置信息
 * @Auther: Anryg
 * @Date: 2020/10/10 14:49
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")/**consul配置中的前缀，该注解必须跟@Configuration一起使用，否则会报错*/
@RefreshScope/**支持热部署*/
public class ElasticsearchProperty {
    private int http_port;/**必须要跟consul配置中的key保持一致*/
    private int tcp_port;
    private String name;
    private String nodes;

    @Bean
    @Primary/**只适用于ElasticsearchTemplate*/
    public ElasticsearchRestTemplate setESTemplate(){
        String[] hosts = nodes.split(",");
        ArrayList<HttpHost> esHostList = new ArrayList<>(hosts.length);
        for (String host:hosts) esHostList.add(new HttpHost(host,http_port));
        RestClientBuilder restClientBuilder = RestClient.builder(esHostList.toArray(new HttpHost[esHostList.size()]));
        RestHighLevelClient highClient = new RestHighLevelClient(restClientBuilder);
        //RestClientBuilder restClientBuilder = RestClient.builder(assembleESHost().toArray(new HttpHost[3]));
        return new ElasticsearchRestTemplate(highClient);
    }

    @Bean
    @Primary/**只适用于ElasticsearchRepository的实现类*/
    public ElasticsearchRestClientProperties setESProperty(){
        ElasticsearchRestClientProperties property = new ElasticsearchRestClientProperties();
        String[] hosts = nodes.split(",");
        List<String> urlList = new ArrayList<>(hosts.length);
        for (String host:hosts){
            String url = "http://" + host + ":" + http_port;
            urlList.add(url);
        }
        property.setUris(urlList);
        return property;
    }


}
