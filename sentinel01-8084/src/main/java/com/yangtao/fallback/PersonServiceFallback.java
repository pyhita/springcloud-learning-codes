package com.yangtao.fallback;

import com.yangtao.entity.Person;

/**
 * @Author: kante_yang
 * @Date: 2024/1/12
 */
// 自定义降级类
public class PersonServiceFallback {

    public static Person personFallback(Throwable t) {

        return Person.builder().name(t.getMessage()).age(-1).build();
    }
}
