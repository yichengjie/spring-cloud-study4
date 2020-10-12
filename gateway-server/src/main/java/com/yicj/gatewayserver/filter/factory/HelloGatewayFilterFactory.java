package com.yicj.gatewayserver.filter.factory;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class HelloGatewayFilterFactory
        extends AbstractGatewayFilterFactory<HelloGatewayFilterFactory.Config> {
    /**
     * 定义可以再yaml中声明的属性变量
     */
    private static final String TYPE = "type";
    private static final String OP = "op";


    public HelloGatewayFilterFactory(){
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(TYPE, OP);
    }


    @Override
    public GatewayFilter apply(Config config) {
        return new InnerFilter(config);
    }

    /**
     * 创建一个内部类，来实现2个接口，指定顺序
     */
    private class InnerFilter implements GatewayFilter, Ordered {
        private Config config;

        InnerFilter(Config config) {
            this.config = config;
        }
        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            log.info(" ===>   pre 自定义过滤器工厂 AAAA  " + this.getClass().getSimpleName());
            boolean root = "root".equals(config.getOp());
            if (root) {
                log.info(" ===> is root ");
            } else {
                log.info(" ===>   is no root ");
            }
            // 在then方法里的，相当于aop中的后置通知
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info(" ===>   post 自定义过滤器工厂 AAAA " + this.getClass().getSimpleName());
            }));
        }

        @Override
        public int getOrder() {
            return -100;
        }
    }

    @Data
    public static class Config {
        //过滤类型
        private String type;
        //操作
        private String op;
    }
}
