package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @DESC: Kafka配置信息
 * @Auther: Anryg
 * @Date: 2020/10/10 15:07
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "kafka")
@RefreshScope
public class KafkaProperty {
    private String hosts;
    public String getHosts() {
        return hosts;
    }
}
