package com.yangtao.controller;

import com.yangtao.entity.Person;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

        try {
            log.info("log start");
            Thread.sleep(5000);
            log.info("log end");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return Person.builder().name("kante").age(22).build();
    }

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2024, 1, 10);
        LocalTime time = LocalTime.of(9, 22, 22);
        LocalDateTime localDateTime = LocalDateTime.of(date, time);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        System.out.println(zonedDateTime);
    }

}
