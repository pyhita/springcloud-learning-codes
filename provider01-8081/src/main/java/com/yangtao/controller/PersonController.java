package com.yangtao.controller;

import com.yangtao.entity.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kante_yang
 * @Date: 2024/1/8
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    @GetMapping
    public Person person() {
        return Person.builder().name("kante").age(22).build();
    }

}
