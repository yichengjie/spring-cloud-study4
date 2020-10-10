package com.yicj.eurekaclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/client")
public class TestController {
    @GetMapping("/add")
    public Integer add(Integer a, Integer b){
        return a + b ;
    }
}
