package com.yangtao.service;

import com.yangtao.entity.Person;
import com.yangtao.fallback.PersonServiceFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: kante_yang
 * @Date: 2024/1/12
 */
@FeignClient(value = "service-provider",
    path = "/person",
    fallback = PersonServiceFeignFallback.class
)
public interface PersonFeignService {

    @GetMapping("/kante")
    Person person();
}
