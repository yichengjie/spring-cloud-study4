package com.yicj.gatewayserver.filter.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


// 统一某个或则某种路由的处理时长
// 这个过滤器无法统计时间，ExampleGatewayFilterFactory这个可以统计
@Slf4j
public class CustomGatewayFilter implements GatewayFilter, Ordered {
    private  static final String COUNT_START_TIME = "COUNT_START_TIME" ;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(COUNT_START_TIME, System.currentTimeMillis()) ;
        log.info("-----> start execute ....");
        return chain.filter(exchange).then(
            Mono.fromRunnable(()->{
                Long startTime = exchange.getAttribute(COUNT_START_TIME) ;
                Long endTime = System.currentTimeMillis()  ;
                log.info("---> startTime: {}, endTime : {}", startTime, endTime);
                log.info("=====> " + exchange.getRequest().getURI().getRawPath() +": " + (endTime -startTime) +"ms");
            })
        );
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
