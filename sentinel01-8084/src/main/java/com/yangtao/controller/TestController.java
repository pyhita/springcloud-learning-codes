package com.yangtao.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kante_yang
 * @Date: 2024/1/16
 */
@RestController
@Slf4j
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    @SentinelResource(
        value = "complex"
    )
    @GetMapping("/complex")
    public String complex(Integer id, String name) {

        return String.format("complex: id = %s, name = %s", id, name);
    }

}
