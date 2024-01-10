package com.yangtao.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @Author: kante_yang
 * @Date: 2024/1/10
 */
@Component
@RefreshScope
@Data
public class Dog {

    @Value("${dog.name}")
    private String name;
}
