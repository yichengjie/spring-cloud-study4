package com.yicj.gatewayserver.filter.global;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

//@Component
public class AuthSignatureFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authToken = exchange.getRequest().getQueryParams().getFirst("authToken");
        if (StringUtils.isBlank(authToken)){
            // 当请求不携带token或则token为空时，直接设置请求状态码为401返回
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED) ;
            return exchange.getResponse().setComplete() ;
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -400;
    }
}
