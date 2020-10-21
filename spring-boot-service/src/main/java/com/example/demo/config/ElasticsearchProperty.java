package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

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
}
