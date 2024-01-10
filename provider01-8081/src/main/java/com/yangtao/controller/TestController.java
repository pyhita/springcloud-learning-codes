package com.yangtao.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kante_yang
 * @Date: 2024/1/10
 */
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    @GetMapping("/header")
    public String header(HttpServletRequest request) {
        String traceId = request.getHeader("x-trace-id");
        return "traceId = " + traceId;
    }

    @GetMapping("/params")
    public String params(HttpServletRequest request) {
        String kante = request.getParameter("kante");
        return "kante = " + kante;
    }

    @GetMapping("/limit")
    public String limit() {
        return "limit request";
    }

}
