package com.example.demo.dao.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

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
    private String login_url;
    private String username;
    private String password;

    @Bean
    public JdbcTemplate createJdbcTemplate(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver_name);
        dataSource.setUrl(login_url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return new JdbcTemplate(dataSource);
    }
}
