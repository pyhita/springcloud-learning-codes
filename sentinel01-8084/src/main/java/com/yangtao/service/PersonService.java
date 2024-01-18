package com.yangtao.service;

import com.yangtao.entity.Person;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: kante_yang
 * @Date: 2024/1/16
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {

    public List<Person> listPerson() {
        return Arrays.asList(
            Person.builder().name("person1").age(1).build(),
            Person.builder().name("person2").age(2).build()
        );
    }

}
