package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @DESC: 重新覆盖spring容器默认的DataSource对象，默认的DataSource对象会读取application.yml里面spring.datasource的配置
 * @Auther: Anryg
 * @Date: 2020/10/16 20:00
 */
@Configuration
//@ConditionalOnMissingClass("DataSource.class")
public class DataSourceConfig {

    @Autowired
    private MysqlProperty mysqlProperty;

    @Bean
    public DataSource createDataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(mysqlProperty.getDriver_name());
        ds.setUrl(mysqlProperty.getUrl());
        ds.setUsername(mysqlProperty.getUsername());
        ds.setPassword(mysqlProperty.getPassword());
        return ds;
    }
}
