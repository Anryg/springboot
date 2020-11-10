package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @DESC: 通过读取consul上的配置来覆盖默认的MySQL配置项
 * @Auther: Anryg
 * @Date: 2020/10/16 19:02
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mysql")
@RefreshScope
public class MysqlProperty {
    private String driver_name;
    private String url;
    private String username;
    private String password;

    @Bean
    public DataSource createDataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver_name);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
}
