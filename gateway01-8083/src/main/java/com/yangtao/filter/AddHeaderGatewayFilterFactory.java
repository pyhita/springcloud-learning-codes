package com.yangtao.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * @Author: kante_yang
 * @Date: 2024/1/10
 */
@Component
public class AddHeaderGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            ServerHttpRequest changeRequest = exchange.getRequest()
                .mutate()
                .header(config.getName(), config.getValue())
                .build();

            ServerWebExchange webExchange = exchange.mutate()
                .request(changeRequest)
                .build();

            return chain.filter(webExchange);
        };
    }
}
