package com.yangtao.controller;

import com.yangtao.entity.Person;
import com.yangtao.service.PersonService;
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
@RequestMapping("/person")
@Slf4j
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public Person person() {
        return personService.person();
    }
}
