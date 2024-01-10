package com.yangtao;

import com.yangtao.bean.Dog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Author: kante_yang
 * @Date: 2024/1/8
 */
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ProviderApplication.class, args);
        Dog dog = (Dog) context.getBean("dog");
        System.out.println(dog);
    }
}