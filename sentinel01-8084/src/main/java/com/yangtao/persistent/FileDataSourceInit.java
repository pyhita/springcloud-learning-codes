package com.yangtao.persistent;

import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.datasource.FileWritableDataSource;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @Author: kante_yang
 * @Date: 2024/1/18
 */
public class FileDataSourceInit implements InitFunc {

    @Override
    public void init() throws Exception {
        File ruleFileDir = new File("/Users/kante/");
        if (!ruleFileDir.exists()) {
            ruleFileDir.mkdirs();
        }

        // 读取并保存各种规则
        readWriteRuleFileFlow(ruleFileDir.getPath());
        readWriteRuleFileDegrade(ruleFileDir.getPath());
    }

    // Degrade rule
    private void readWriteRuleFileDegrade(String path) throws FileNotFoundException {
        String ruleFile = path + "/degrade-rule.json";
        createRuleFile(ruleFile);
        ReadableDataSource<String, List<DegradeRule>> ds = new FileRefreshableDataSource<>(
            ruleFile, source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {})
        );
        // 将可读数据源注册到FlowManager
        DegradeRuleManager.register2Property(ds.getProperty());

        WritableDataSource<List<DegradeRule>> wds = new FileWritableDataSource<>(ruleFile, this::encodeJson);
        // 将可写数据注册到transport 的WriteableDataSourceRegistry 模块中
        // 这样收到控制台推送的规则，Sentinel 会先更新到内存，然后将规则写入到文件中
        WritableDataSourceRegistry.registerDegradeDataSource(wds);
    }

    // Flow rule
    private void readWriteRuleFileFlow(String path) throws Exception {
        String ruleFile = path + "/flow-rule.json";
        createRuleFile(ruleFile);
        ReadableDataSource<String, List<FlowRule>> ds = new FileRefreshableDataSource<>(
            ruleFile, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {})
        );
        // 将可读数据源注册到FlowManager
        FlowRuleManager.register2Property(ds.getProperty());

        WritableDataSource<List<FlowRule>> wds = new FileWritableDataSource<>(ruleFile, this::encodeJson);
        // 将可写数据注册到transport 的WriteableDataSourceRegistry 模块中
        // 这样收到控制台推送的规则，Sentinel 会先更新到内存，然后将规则写入到文件中
        WritableDataSourceRegistry.registerFlowDataSource(wds);
    }

    private void createRuleFile(String ruleFile) {
        File file = new File(ruleFile);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private <T> String encodeJson(T t) {
        return JSON.toJSONString(t);
    }
}
