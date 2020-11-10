package com.example;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @DESC: Zuul网关，请求的入口
 * @Auther: Anryg
 * @Date: 2020/10/20 22:18
 */
@SpringBootApplication
@EnableZuulProxy
@EnableAdminServer
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
