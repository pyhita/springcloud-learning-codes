package com.yangtao.fallback;

import com.yangtao.entity.Person;
import com.yangtao.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PersonFallback implements PersonService {
    @Override
    public Person person() {
        log.info("远程调用异常或者 timeout！");
        return Person.builder().name("Default").age(33).build();
    }
}
