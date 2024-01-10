package com.yangtao.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: kante_yang
 * @Date: 2024/1/10
 */
@Configuration
public class RouteFilterConfig {

    @Bean
    public RouteLocator fallback(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("fallback_route",
                ps -> ps.path("/test/fallback")
                    .filters(fs -> fs.circuitBreaker(config -> {
                        config.setName("anyname");
                        config.setFallbackUri("forward:/fb");
                    }))
                    .uri("http://localhost:8081"))
            .build();
    }

}
