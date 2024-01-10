package com.yangtao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

/**
 * @Author: kante_yang
 * @Date: 2024/1/8
 */
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//
//        return builder.routes()
//            .route("baidu_route",
//                ps -> ps.path("/**").uri("https://www.baidu.com"))
//            .build();
//    }

    // 生成限流键
    @Bean
    public KeyResolver useKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest()
            .getRemoteAddress()
            .getHostName());
    }
}