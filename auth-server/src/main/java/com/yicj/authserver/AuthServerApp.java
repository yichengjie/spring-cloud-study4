package com.yicj.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthServerApp{
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApp.class, args) ;
    }
}
