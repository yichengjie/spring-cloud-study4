package com.yicj.eurekaclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Slf4j
@RestController
public class TestController {
    @GetMapping("/add")
    public Integer add(Integer a, Integer b, @RequestHeader(value = "X-Token", required = false) String token){
        log.info("=======> token : " + token);
        return a + b ;
    }

    @RequestMapping("/test")
    public String test(HttpServletRequest request){
        log.info("-----------------------header-----------------------");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String key = (String)headerNames.nextElement() ;
            log.info("{} : {}",key, request.getHeader(key));
        }
        log.info("-----------------------header-----------------------");
        return "hello world" ;
    }
}
