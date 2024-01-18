package com.yangtao.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @Author: kante_yang
 * @Date: 2024/1/18
 */
@Component
@Slf4j
public class TwoGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            log.info("{} - {}", config.getName(), config.getValue());
            return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    log.info(config.getName() + " - " + config.getValue());
                })
            );
        };
    }
}
