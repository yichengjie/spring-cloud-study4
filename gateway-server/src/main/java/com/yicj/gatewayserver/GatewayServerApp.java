package com.yicj.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayServerApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApp.class, args) ;
    }
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route(r -> r.path("/jd")
//                .uri("http://jd.com:80/")//https://www.jd.com/
//                .id("jd_route")).build() ;
//    }
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
//        // 生成当前时间早一个小时的UTC
//        ZonedDateTime minusTime = LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault()) ;
//        return builder.routes()
//                .route("after_route", r -> r.after(minusTime).uri("http://www.baidu.com"))
//                .build() ;
//    }
}
