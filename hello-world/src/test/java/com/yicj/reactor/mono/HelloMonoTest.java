package com.yicj.reactor.mono;

import org.junit.Test;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

public class HelloMonoTest {

    @Test
    public void empty(){
        Mono.empty().subscribe(System.out::println) ;
    }

    @Test
    public void just(){
        Mono.just("foo").subscribe(System.out::println);
    }

    @Test
    public void justOrEmpty(){
        Mono.justOrEmpty(null).subscribe(System.out::println) ;
        Mono.justOrEmpty("justOrEmpty1").subscribe(System.out::println) ;
    }

    @Test
    public void fromRunnable(){
        Mono.fromRunnable(()-> {
            System.out.println("thread run..");
            throw new RuntimeException("thread run error") ;
        }).subscribe(System.out::println, System.err::println) ;
    }

    @Test
    public void fromCallable(){
        Mono.fromCallable(() -> "callable run").subscribe(System.out::println);
    }

    @Test
    public void fromSupplier(){
        Mono.fromSupplier(()->"create from supplier").subscribe(System.out::println) ;
    }
}
