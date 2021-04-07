package com.alibaba.csp.sentinel.dashboard.rule.memory;

import com.alibaba.csp.sentinel.dashboard.datasource.RuleConfigTypeEnum;
import com.alibaba.csp.sentinel.dashboard.rule.AbstractpersistentRuleApiClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * It only makes sure this project starting success, don't have any function.
 * It can not be used to manage rules as memory client.
 * When need to manage rules as memory client, should use the SentinelApiClient in package com.alibaba.csp.sentinel.dashboard.client.
 *
 * @Author Jiajiangnan
 * @E-mail jiajiangnan.office@foxmail.com
 * @Date 2020/8/31
 * @Version 1.0
 */
public class MemoryApiClient<T> extends AbstractpersistentRuleApiClient<T> {
    private static final Logger log = LoggerFactory.getLogger(MemoryApiClient.class);

    @Override
    public List<T> fetch(String app, RuleConfigTypeEnum configType) throws Exception {
        log.info("MemoryApiClient fetch");
        return null;
    }

    @Override
    public void publish(String app, RuleConfigTypeEnum configType, List<T> rules) throws Exception {
        log.info("MemoryApiClient publish");
    }
}
