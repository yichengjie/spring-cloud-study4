package com.yicj.zuulserver.config;

import com.yicj.zuulserver.zuul.DynamicZuulRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Autowired
    private ZuulProperties zuulProperties ;
    @Autowired
    private ServerProperties serverProperties ;

    @Bean
    public DynamicZuulRouteLocator routeLocator(){
        DynamicZuulRouteLocator routeLocator = new DynamicZuulRouteLocator(
                serverProperties.getServlet().getContextPath(),
                zuulProperties
        ) ;
        return routeLocator ;
    }

   /* @Bean
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
    }*/
}
