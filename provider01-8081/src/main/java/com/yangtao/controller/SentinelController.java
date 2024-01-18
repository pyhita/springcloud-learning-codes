package com.yangtao.controller;

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
@RequiredArgsConstructor
@RequestMapping("/sentinel")
public class SentinelController {

    @GetMapping("/get_route")
    public String getRoute() {

        return "service provider get_route";
    }

    @GetMapping("/list_route")
    public String listRoute() {

        return "list provider lis_route";
    }
}
