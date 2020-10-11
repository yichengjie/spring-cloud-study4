package com.yicj.gatewayserver.hello;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class HelloWorldTest {

    @Test
    public void test1(){
        String minTime = ZonedDateTime.now().minusHours(1).format(DateTimeFormatter.ISO_ZONED_DATE_TIME) ;
        System.out.println(minTime);
    }

    @Test
    public void test2(){
        ZonedDateTime zonedDateTime = LocalDateTime.now().minusHours(1).atZone(ZoneId.systemDefault());
        String format = zonedDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
        System.out.println(format);
    }
}
