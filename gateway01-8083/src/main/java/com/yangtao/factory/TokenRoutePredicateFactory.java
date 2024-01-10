package com.yangtao.factory;

import com.yangtao.factory.TokenRoutePredicateFactory.Config;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @Author: kante_yang
 * @Date: 2024/1/10
 */
@Component
public class TokenRoutePredicateFactory extends AbstractRoutePredicateFactory<Config> {
    public TokenRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("token");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {

        return exchange -> {
            List<String> tokens = exchange.getRequest().getQueryParams().get("token");
            if (Objects.isNull(tokens) || tokens.size() == 0) return false;

            return tokens.contains(config.getToken());
        };
    }

    @Data
    public static class Config {
        private String token;
    }
}
