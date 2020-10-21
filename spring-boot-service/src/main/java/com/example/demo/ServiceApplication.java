package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication  /**默认会扫描该类存在的包以及其子包下所有的类，并根据其注解进行初始化、实例化、装载等操作*/
@EnableDiscoveryClient /**开启服务发现，作为客户端注册到consul，与配置中心功能独立*/
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}
}
