package com.yangtao.fallback;

import com.yangtao.entity.Person;
import com.yangtao.service.PersonFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: kante_yang
 * @Date: 2024/1/12
 */
@Component
@Slf4j
@RequestMapping("/fallback/person")
public class PersonServiceFeignFallback implements PersonFeignService {

    @Override
    public Person person() {
        log.info("执行服务降级 处理逻辑！");
        return Person.builder().name("fallback").age(-1).build();
    }
}
