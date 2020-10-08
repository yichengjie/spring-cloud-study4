package com.yicj.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient 或@EnableEurekaClient 可省略。
// 只需加上相关依赖，并进行相应配置，即可将微服务注册到服务发现组件上
@EnableDiscoveryClient
@SpringBootApplication
public class ClientAApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientAApplication.class, args) ;
    }
}
