package com.yicj.gatewayserver.filter.factory;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

//这里的命名不许为GatewayFilterFactory结尾，在application.yml中配置时仅需要配置Example即可
@Slf4j
public class ExampleGatewayFilterFactory
        extends AbstractGatewayFilterFactory<ExampleGatewayFilterFactory.Config> {
    private  static final String COUNT_START_TIME = "COUNT_START_TIME" ;
    /**
     * 定义可以再yaml中声明的属性变量
     */
    private static final String TYPE = "type";
    private static final String OP = "op";


    public ExampleGatewayFilterFactory(){
        // 这里需要将自定义的config传过去，否则会报告ClassCastException
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(TYPE, OP);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            boolean root = "root".equals(config.getOp());
            if (root){
                log.info("====> GatewayFilter root");
            } else {
                log.info("====> GatewayFilter customer");
            }
            exchange.getAttributes().put(COUNT_START_TIME, System.currentTimeMillis()) ;
            // 在then方法里的，相当于aop中的后置通知
            return chain.filter(exchange).then(
                Mono.fromRunnable(()->{
                    Long startTime = exchange.getAttribute(COUNT_START_TIME) ;
                    Long endTime = System.currentTimeMillis()  ;
                    // do something
                    log.info("=====> complete ... " + exchange.getRequest().getURI().getRawPath() +": " + (endTime -startTime) +"ms");
                })
            );
        });
    }

    //自定义的config类，用来设置传入的参数
    @Data
    public static class Config {
        //过滤类型
        private String type;
        //操作
        private String op;
    }
}