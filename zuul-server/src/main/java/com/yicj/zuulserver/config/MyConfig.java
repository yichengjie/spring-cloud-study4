package com.yicj.zuulserver.config;

import com.netflix.zuul.ZuulFilter;
import com.yicj.zuulserver.filter.FirstPreFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public FirstPreFilter firstZuulFilter(){
        return new FirstPreFilter() ;
    }
}
