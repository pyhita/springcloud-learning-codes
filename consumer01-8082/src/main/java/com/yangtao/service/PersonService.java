package com.yangtao.service;

import com.yangtao.entity.Person;
import com.yangtao.fallback.PersonFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: kante_yang
 * @Date: 2024/1/8
 */
@FeignClient(
        value = "service-provider",
        path = "/person/kante",
        fallback = PersonFallback.class
)
public interface PersonService {

    @GetMapping
    Person person();
}
