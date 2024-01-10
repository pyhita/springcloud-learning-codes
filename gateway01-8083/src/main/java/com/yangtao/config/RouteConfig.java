package com.yangtao.config;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: kante_yang
 * @Date: 2024/1/10
 */
@Configuration
public class RouteConfig {

//    // 添加after 断言工厂
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        ZonedDateTime dateTime = LocalDateTime.now().minusDays(5)
//            .atZone(ZoneId.systemDefault());
//
//        return builder.routes()
//            .route("after_route",
//                ps -> ps.after(dateTime).uri("https://www.baidu.com"))
//            .build();
//    }

    //    Cookie断言工厂
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//
//        return builder.routes()
//            .route("baidu_route",
//                ps -> ps.cookie("City", "beijing").uri("https://www.baidu.com"))
//            .build();
//    }

//    Header断言工厂
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//
//        return builder.routes()
//            .route("baidu_route",
//                ps -> ps.header("City", "beijing").uri("https://www.baidu.com"))
//            .build();
//    }



//    // 添加Path 断言工厂
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        ZonedDateTime dateTime = LocalDateTime.now().minusDays(5)
//            .atZone(ZoneId.systemDefault());
//
//        return builder.routes()
//            .route("provider_route",
//                ps -> ps.path("/person/kante/**")
//                    .uri("http://localhost:8081"))
//            .build();
//    }


    // 添加Query 断言工厂
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {

        return builder.routes()
            .route("provider_route",
                ps -> ps.query("color", "gr.+")
                    .and()
                    .query("size")
                    .uri("https://www.baidu.com/"))
            .build();
    }

}
