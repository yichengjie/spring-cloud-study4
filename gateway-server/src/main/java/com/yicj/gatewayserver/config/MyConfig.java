package com.yicj.gatewayserver.config;

import com.yicj.gatewayserver.anno.AvoidScan;
import com.yicj.gatewayserver.filter.factory.ExampleGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@AvoidScan
@Configuration
public class MyConfig {

    @Bean
    public ExampleGatewayFilterFactory exampleGatewayFilterFactory(){
        return new ExampleGatewayFilterFactory();
    }
}