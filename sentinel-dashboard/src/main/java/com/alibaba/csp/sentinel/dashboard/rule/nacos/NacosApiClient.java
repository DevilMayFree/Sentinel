package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.RuleConfigTypeEnum;
import com.alibaba.csp.sentinel.dashboard.datasource.ds.nacos.NacosProperties;
import com.alibaba.csp.sentinel.dashboard.rule.AbstractpersistentRuleApiClient;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Jiajiangnan
 * @E-mail jiajiangnan.office@foxmail.com
 * @Date 2020/8/31
 * @Version 1.0
 */
public class NacosApiClient<T> extends AbstractpersistentRuleApiClient<T> {
    private static final Logger log = LoggerFactory.getLogger(NacosApiClient.class);

    @Autowired
    private NacosProperties nacosProperties;
    @Autowired
    private ConfigService configService;

    private String getRuleConfigId(String appName, RuleConfigTypeEnum ruleFix) {
        log.info("NacosApiClient getRuleConfigId");
        appName = StringUtils.isBlank(appName) ? "Sentinel" : appName;
        log.info("NacosApiClient getRuleConfigId. appName:{}", appName);
        return String.format("%s-%s", appName, ruleFix.getValue());
    }

    @Override
    public List<T> fetch(String app, RuleConfigTypeEnum configType) throws Exception {
        log.info("NacosApiClient fetch");
        String ruleName = this.getRuleConfigId(app, configType);
        String rulesJson = configService.getConfig(ruleName, nacosProperties.getGroupId(), 3000);
        log.info("NacosApiClient fetch.ruleName:{},rulesJson:{}", ruleName, rulesJson);
        if (StringUtil.isEmpty(rulesJson)) {
            return (List<T>) new ArrayList();
        }
        return JSON.parseArray(rulesJson, configType.getClazz());
    }

    @Override
    public void publish(String app, RuleConfigTypeEnum configType, List<T> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");
        log.info("NacosApiClient publish");
        if (rules == null) {
            return;
        }
        String ruleName = this.getRuleConfigId(app, configType);
        String groupId = nacosProperties.getGroupId();
        String rulesJson = JSON.toJSONString(rules, true);
        log.info("NacosApiClient publish. ruleName:{},groupId:{},rulesJson:{}", ruleName, groupId, rulesJson);
        configService.publishConfig(ruleName, groupId, rulesJson);
    }

}
