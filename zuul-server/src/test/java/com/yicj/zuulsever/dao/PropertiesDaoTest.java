package com.yicj.zuulsever.dao;

import com.yicj.zuulserver.ZuulServerApp;
import com.yicj.zuulserver.dao.PropertiesDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.Set;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZuulServerApp.class)
public class PropertiesDaoTest {

    @Autowired
    private PropertiesDao propertiesDao ;

    @Test
    public void getProperties(){
        Map<String, ZuulProperties.ZuulRoute> properties = propertiesDao.getProperties();
        Set<Map.Entry<String, ZuulProperties.ZuulRoute>> entries = properties.entrySet();
        for (Map.Entry<String, ZuulProperties.ZuulRoute> entry: entries){
            String key = entry.getKey();
            ZuulProperties.ZuulRoute value = entry.getValue();
            log.info("======> key : {}， value: {}",key, value);
        }
    }
}
