package com.yangtao.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Author: kante_yang
 * @Date: 2024/1/16
 */
@Component
public class PersonRequestOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest request) {
        String source = request.getParameter("source");

        if (!StringUtils.hasText(source)) {
            source = "serviceA";
        }
        // source 将作为请求的来源
        return source;
    }
}
