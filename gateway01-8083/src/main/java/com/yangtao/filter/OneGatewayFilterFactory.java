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
public class OneGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            long start = System.currentTimeMillis();
            log.info("{} - {} 开始时间: {}", config.getName(), config.getValue(), start);
            exchange.getAttributes().put("start", start);
            return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = (Long) exchange.getAttributes().get("start");
                    long endTime = System.currentTimeMillis();
                    long elapsedTime = endTime - startTime;
                    log.info("{} - {} 结束时间：{}", config.getName(), config.getValue(),
                        elapsedTime);
                    log.info("该filter 执行用时(毫秒)： {}", elapsedTime);
                })
            );
        };
    }
}
