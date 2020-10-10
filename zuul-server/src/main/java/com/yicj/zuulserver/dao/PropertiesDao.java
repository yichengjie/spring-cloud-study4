package com.yicj.zuulserver.dao;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class PropertiesDao {
    @Autowired
    private JdbcTemplate jdbcTemplate ;
    private static final String SQL = "select * from dynamic_zuul_route where enable = 1" ;

    public Map<String, ZuulProperties.ZuulRoute> getProperties(){
        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>() ;
        List<ZuulRouteEntity> list = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(ZuulRouteEntity.class));
        list.stream()
            .filter(item -> StringUtils.isNotBlank(item.getPath()))
            .forEach(item -> {
                ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
                BeanUtils.copyProperties(item, zuulRoute);
                routes.put(zuulRoute.getPath(), zuulRoute) ;
            }) ;
        return routes ;
    }


    @Data
    static class ZuulRouteEntity{
       private String id ;
       private String path ;
       private String serviceId ;
       private String url ;
       private boolean stripPrefix ;
       private Boolean retryable ;
       private boolean enable ;
       private String description ;
    }

}
