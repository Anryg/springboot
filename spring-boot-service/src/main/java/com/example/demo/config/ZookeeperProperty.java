package com.example.demo.config;

import com.example.demo.aop.annotation.LogAnnotation;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @DESC: Zookeeper的配置信息
 * @Auther: Anryg
 * @Date: 2020/10/10 15:09
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "zookeeper")
@RefreshScope
public class ZookeeperProperty {
    private String hosts;
    private int port;
}
