package com.yicj.zuulserver.zuul;

import com.yicj.zuulserver.dao.PropertiesDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import java.util.LinkedHashMap;
import java.util.Map;

public class DynamicZuulRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {
    @Autowired
    private ZuulProperties properties ;
    @Autowired
    private PropertiesDao propertiesDao ;

    public DynamicZuulRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties ;
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        Map<String, ZuulProperties.ZuulRoute> routeMap = new LinkedHashMap<>() ;
        routeMap.putAll(super.locateRoutes());
        routeMap.putAll(propertiesDao.getProperties());
        Map<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>() ;
        routeMap.forEach((key,value) ->{
            String path = key ;
            if (!path.startsWith("/")){
                path = "/" + path ;
            }
            if (StringUtils.isNotBlank(this.properties.getPrefix())){
                path = this.properties.getPrefix() + path ;
                if (!path.startsWith("/")){
                    path += "/" ;
                }
                values.put(path,value) ;
            }
        });
        return values;
    }
}
