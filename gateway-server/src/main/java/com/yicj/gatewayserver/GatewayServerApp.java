package com.yicj.gatewayserver;

import com.yicj.gatewayserver.anno.AvoidScan;
import com.yicj.gatewayserver.filter.gateway.CustomGatewayFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

//@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = AvoidScan.class)})
public class GatewayServerApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApp.class, args);
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
//        return builder.routes()
//                .route(r -> r.path("/baidu")
//                .uri("http://baidu.com:80/")
//                .id("baidu_route")).build() ;
//    }
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
//        // 生成当前时间早一个小时的UTC
//        ZonedDateTime minusTime = LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault()) ;
//        return builder.routes()
//                .route("after_route", r -> r.after(minusTime).uri("http://www.baidu.com"))
//                .build() ;
//    }
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("method_route", r -> r.method("GET")
//                        .uri("lb://eureka-client"))
//                .build();
//    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("query_route", r -> r.query("foo","baz")
//                        .uri("lb://eureka-client"))
//                .build();
//    }
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
//        return builder.routes().route("add_request_header_route",
//                r -> r.path("/test/**")
//                    .filters(f -> f.addRequestHeader("X-Request-Acme","ValueB"))
//                    .uri("http://localhost:8081"))
//                   //.uri("lb://eureka-client"))
//                .build() ;
//    }

    //    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route("rewritepath_route",
//                        r -> r.path("/foo/**")
//                                .filters(f-> f.rewritePath("/foo/(?<segment>.*)","/$\\{segment}"))
//                        .uri("http://www.baidu.com")
//                ).build() ;
//    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route("add_response_header_route",
//                        r -> r.path("/test")
//                        .filters(f -> f.addResponseHeader("X-Response-Foo","Bar"))
//                        .uri("lb://eureka-client")
//                ).build() ;
//    }

    // 重试次数
//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
//        return builder.routes()
//                .route("retry_route",
//                        r -> r.path("/test/**")
//                                .filters(f -> f.retry(retryConfig -> retryConfig.setRetries(2).setStatuses(HttpStatus.INTERNAL_SERVER_ERROR)))
//                                //.uri("http://localhost:8081/retry?key=abc&count=2")
//                                .uri("lb://eureka-client")
//                ).build() ;
//    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
//        return builder.routes()
//            .route(r -> r.path("/test/**")
//                    .filters(f -> f.filter(new CustomGatewayFilter()))
//                    .uri("lb://eureka-client")
//                    .order(0)
//                    .id("custom_filter")
//            ).build() ;
//    }

}
