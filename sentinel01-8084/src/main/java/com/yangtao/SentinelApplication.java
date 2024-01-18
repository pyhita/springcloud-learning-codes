package com.yangtao;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;

/**
 * @Author: kante_yang
 * @Date: 2024/1/12
 */
@SpringBootApplication
@EnableFeignClients
public class SentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class, args);
        // initRules();
    }

    private static void initRules() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = SentinelApplication.configSlowDegradeRule();
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }

    // 配置降级规则
    private static DegradeRule configSlowDegradeRule() {
        DegradeRule rule = new DegradeRule();
        // 设置资源名
        rule.setResource("getPerson");
        // 制定降级策略
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        // 设置RT
        rule.setCount(2);
        rule.setSlowRatioThreshold(0.3);
        rule.setTimeWindow(10);
        rule.setStatIntervalMs(1000);
        rule.setMinRequestAmount(3);
        return rule;
    }

    // 配置流控规则
    private static FlowRule configFlowControlRule() {
        FlowRule rule = new FlowRule("getPerson");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(3);
        rule.setLimitApp("default");
        return rule;
    }

    // 配置来源控制 规则
    private static FlowRule configQpsControlRule() {
        FlowRule rule = new FlowRule("getPerson");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(3);
        // 制定请求的来源
        rule.setResource("serviceA");
        return rule;
    }

    // 配置授权规则
    private static AuthorityRule configAuthorityRule() {
        AuthorityRule rule = new AuthorityRule();
        rule.setResource("getPerson");
        rule.setStrategy(RuleConstant.AUTHORITY_WHITE);
        rule.setLimitApp("serviceA,serviceB");

        return rule;
    }

    // 配置热点规则
    private static ParamFlowRule configParamFlowRule() {
        ParamFlowRule rule = new ParamFlowRule();
        rule.setResource("complex");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(3);
        rule.setParamIdx(0);
        rule.setDurationInSec(1);

        // 设置参数例外项
        List<ParamFlowItem> items = new ArrayList<>();
        items.add(nameParamItem("admin", 100));
        items.add(nameParamItem("adminMis", 1000));
        rule.setParamFlowItemList(items);

        return rule;
    }

    private static ParamFlowItem nameParamItem(String value, int count) {
        ParamFlowItem item = new ParamFlowItem();
        item.setClassType(String.class.getName());
        item.setObject(value);
        item.setCount(count);

        return item;
    }

    @Bean
    public ServerCodecConfigurer serverCodecConfigurer() {
        return ServerCodecConfigurer.create();
    }
}
