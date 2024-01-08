package com.yangtao.service;

import com.yangtao.entity.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: kante_yang
 * @Date: 2024/1/8
 */
@FeignClient(value = "service-provider", path = "/person")
public interface PersonService {

    @GetMapping
    Person person();
}
