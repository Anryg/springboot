package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @DESC: Zuul网关，请求的入口
 * @Auther: Anryg
 * @Date: 2020/10/20 22:18
 */
@SpringBootApplication
@EnableDiscoveryClient /**自动发现断开后重新连上的服务，一定要加上，否则服务如果在网关后启动会导致网关无法发现*/
@EnableZuulProxy
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
