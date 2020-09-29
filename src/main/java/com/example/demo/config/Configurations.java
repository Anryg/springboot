package com.example.demo.config;

import com.ecwid.consul.v1.ConsulClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @DESC: 配置springboot中各个需要的固定配置
 * @Auther: Anryg
 * @Date: 2020/9/15 17:00
 */
@Configuration(value = "configs")
@Slf4j
@Deprecated /**通过spring cloud的consul配置中心功能来代替*/
public class Configurations {
    private ConsulClient client = new ConsulClient("192.168.211.109");

    /**
     * @DESC: 获取某个单独配置
     * */
    private String getSingleConf(String conPath) throws Exception {
        try {
            return client.getKVValue(conPath).getValue().getDecodedValue();
        } catch (Exception e) {
            log.error("请检查Consul配置："+ conPath,e);
            throw new Exception("请检查Consul配置："+ conPath,e);
        }
    }

    /**
     * @DESC: 获取ES的host信息
     * */
    @Bean(name = "esHosts")
    @Scope(value = "singleton")
    public String[] getESHosts() throws Exception {
        String hosts = getSingleConf("es/cluster.nodes");
        return hosts.split(",");
    }

    /**
     * @DESC: 获取ES的http端口
     * */
    @Bean(name = "esPort")
    @Scope(value = "singleton")
    public int getESPort() throws Exception {
        return Integer.valueOf(getSingleConf("es/cluster.http.port"));
    }

}
