package com.yangtao.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudConfig {

    @Bean
    public Logger.Level feignLevel() {

        return Logger.Level.FULL;
    }
}
