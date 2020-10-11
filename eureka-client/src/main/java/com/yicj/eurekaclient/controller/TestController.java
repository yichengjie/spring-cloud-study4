package com.yicj.eurekaclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GetMapping
    public String hello(){
        return "hello world" ;
    }

    @GetMapping("/test/head")
    public String head(@RequestHeader(value = "X-Request-Acme", required = false) String acme){
        log.info("========> acme: {}", acme);
        return "hello head" ;
    }
}
