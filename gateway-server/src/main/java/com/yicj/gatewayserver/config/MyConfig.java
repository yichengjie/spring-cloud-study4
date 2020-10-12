package com.yicj.gatewayserver.config;

import com.yicj.gatewayserver.filter.factory.ExampleGatewayFilterFactory;
import com.yicj.gatewayserver.filter.factory.HelloGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@AvoidScan
@Configuration
public class MyConfig {

    @Bean
    public ExampleGatewayFilterFactory exampleGatewayFilterFactory(){
        return new ExampleGatewayFilterFactory();
    }

    @Bean
    public HelloGatewayFilterFactory helloGatewayFilterFactory(){
        return new HelloGatewayFilterFactory() ;
    }
}