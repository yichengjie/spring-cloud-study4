package com.yicj.zuulserver.config;

import com.netflix.zuul.ZuulFilter;
import com.yicj.zuulserver.filter.FirstPreFilter;
import com.yicj.zuulserver.filter.PostFilter;
import com.yicj.zuulserver.filter.SecondPreFilter;
import com.yicj.zuulserver.filter.ThirdPreFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public FirstPreFilter firstZuulFilter(){
        return new FirstPreFilter() ;
    }

    @Bean
    public SecondPreFilter secondZuulFilter(){
        return new SecondPreFilter() ;
    }
    @Bean
    public ThirdPreFilter thirdPreFilter(){
        return new ThirdPreFilter() ;
    }
    @Bean
    public PostFilter postFilter(){
        return new PostFilter() ;
    }
}
