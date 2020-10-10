package com.yicj.eurekaclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {
    @GetMapping("/add")
    public Integer add(Integer a, Integer b, @RequestHeader(value = "X-Token", required = false) String token){
        log.info("=======> token : " + token);
        return a + b ;
    }
}
