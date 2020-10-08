1. 添加依赖
    ```text
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
    ```
 2. 添加配置
     ```text
    eureka:
      instance:
        hostname: localhost
      client:
        register-with-eureka: false
        fetch-registry: false
        service-url:
          defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
      server:
        wait-time-in-ms-when-sync-empty: 0
        enable-self-preservation: false
    ```