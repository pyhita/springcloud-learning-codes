package com.yangtao.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.yangtao.entity.Person;
import com.yangtao.service.PersonFeignService;
import com.yangtao.service.PersonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kante_yang
 * @Date: 2024/1/12
 */
@RestController
@RequestMapping("/person")
@Slf4j
@RequiredArgsConstructor
public class PersonController {

    private final PersonFeignService personFeignService;
    private final PersonService personService;


    // fallback 用来指定降级方法
    // @SentinelResource(fallback = "personFallback",
    //     fallbackClass = PersonServiceFallback.class)
    /* 通过value 自定义资源名 */
    // @SentinelResource(value = "getPerson", fallback = "personFallback")
    // @SentinelResource(value = "getPerson")
    // @SentinelResource(
    //     value = "getPerson",
    //     blockHandler = "personFlowControlHandler", // blockHandler 设定流控处理器
    //     fallback = "personFallback"
    // )

    // 1 fallback 方法级降级
    // @SentinelResource(fallback = "personFallback")
    // 2 fallback 类级降级
    // @SentinelResource(fallback = "personFallback",
    //     fallbackClass = PersonServiceFallback.class)
    // 3 指定资源名称
    // @SentinelResource(value = "getPerson")
    // 4 流量控制 降级
    @SentinelResource(
        value = "getPerson",
        blockHandler = "personFlowControlHandler",
        fallback = "personFallback"
    )
    @GetMapping("/kante")
    public Person person() {
        return personFeignService.person();
    }

    @SentinelResource(
        value = "getPerson2"
    )
    @GetMapping("/kante2")
    public Person person2() {
        return Person.builder().name("kante2").age(22).build();
    }

    public Person personFallback(Throwable e) {
        log.info("person fallback");
        return Person.builder().name(e.getMessage()).age(0).build();
    }

    public Person personFlowControlHandler() {
        log.info("person flow control handler");
        return Person.builder().name("flow control").age(-1).build();
    }

    // @SentinelResource(value = "listPerson")
    // @GetMapping("/list")
    // public List<Person> listPerson() {
    //     Entry entry = null;
    //     try {
    //         // 加载指定实体的流控规则
    //         entry = SphU.entry("getPerson");
    //         // 未被流控阻断时，执行正常的业务逻辑
    //         return Collections.emptyList();
    //     } catch (BlockException e) {
    //         // 发生流控阻断时，捕获异常
    //         return Arrays.asList(Person.builder().name("flow-control").age(-22).build());
    //     } finally {
    //         // 结束资源实体规则
    //         if (entry != null) {
    //             entry.exit();
    //         }
    //     }
    // }

    @GetMapping("/list")
    @SentinelResource(
        value = "listPerson"
    )
    public List<Person> listPersons() {
        return personService.listPerson();
    }

    @GetMapping("/all")
    @SentinelResource(
        value = "listPerson"
    )
    public List<Person> getAllPersons() {
        return personService.listPerson();
    }



}
