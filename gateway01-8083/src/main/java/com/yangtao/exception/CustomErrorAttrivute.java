package com.yangtao.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

/**
 * @Author: kante_yang
 * @Date: 2024/1/10
 */
//@Component
//public class CustomErrorAttrivute extends DefaultErrorAttributes {
//
//    @Override
//    public Map<String, Object> getErrorAttributes(WebRequest webRequest,
//        ErrorAttributeOptions options) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("status", HttpStatus.NOT_FOUND.value());
//        map.put("msg", "对不起，找不到！");
//        return map;
//    }
//}
