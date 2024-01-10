package com.yangtao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kante_yang
 * @Date: 2024/1/10
 */
@RestController
public class FallbackController {

    @GetMapping("/fb")
    public String fallbackHandle() {

        return "This is spring cloud fallback.";
    }
}
