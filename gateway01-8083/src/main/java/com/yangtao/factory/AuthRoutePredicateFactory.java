package com.yangtao.factory;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @Author: kante_yang
 * @Date: 2024/1/10
 */
@Component
public class AuthRoutePredicateFactory extends AbstractRoutePredicateFactory<AuthRoutePredicateFactory.Config> {
    public AuthRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            List<String> pwds = headers.get(config.getUsername());
            if (pwds.contains(config.getPassword())) {
                return true;
            } else {
                return false;
            }
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("username", "password");
    }

    @Data
    public static class Config {
        private String username;
        private String password;
    }
}
