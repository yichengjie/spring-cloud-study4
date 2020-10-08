1. 添加依赖
    ```text
     <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    ```
2. 添加配置
    ```text
    eureka:
      client:
        service-url:
          defaultZone: http://localhost:8761/eureka
    ```