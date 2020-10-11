package com.yicj.eurekaclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String hello(){
        return "hello world" ;
    }

    @GetMapping("/head")
    public String head(@RequestHeader(value = "X-Request-Acme", required = false) String acme){
        log.info("========> acme: {}", acme);
        return "hello head" ;
    }
    private Map<String, AtomicInteger> map = new ConcurrentHashMap<>() ;

    @GetMapping("/retry")
    public String testRetryByException(@RequestParam("key") String key,
                                       @RequestParam("count") int count){
        AtomicInteger num = map.computeIfAbsent(key, s-> new AtomicInteger()) ;
        // 对请求或重试次数计数
        int i = num.incrementAndGet();
        log.warn("重试次数：" + i);
        // 计数i小于重试次数2抛出异常，让Spring Cloud Gateway进行重试
        if (i < count){
            throw new RuntimeException("Deal with failure, please try again!") ;
        }
        //当重试两次时，清空计数,返回重试两次成功
        map.clear();
        return "重试" + count +"次成功！" ;
    }

    @GetMapping("/hystrix")
    public String hystrix(@RequestParam("isSleep") boolean isSleep) throws InterruptedException{
        log.info("isSleep is {}", isSleep);
        // isSleep 为true开始睡眠，睡眠时间大于Gateway中的fallback设置的时间
        if (isSleep){
            TimeUnit.MINUTES.sleep(10) ;
        }
        return "No Sleep" ;
    }

    @GetMapping("/customFilter")
    public String customFilter(){
        try {
            Thread.sleep(1002);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "hello customFilter" ;
    }
}
