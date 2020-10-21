package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @DESC: Redis的配置信息
 * @Auther: Anryg
 * @Date: 2020/10/10 14:57
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "redis")
@RefreshScope
public class RedisProperty {
    private String host;
    private int port;
    private String password;
}
