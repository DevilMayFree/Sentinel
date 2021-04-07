package com.alibaba.csp.sentinel.dashboard.datasource.ds;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Jiajiangnan
 * @E-mail jiajiangnan.office@foxmail.com
 * @Date 2020/8/30
 * @since 1.8.0
 */
@Component
public class DataSourceProperties {

    public static final String PREFIX_DATASOURCE = "ds";
    public static final String NAME_PROVIDER = "pr";

    public static final String VALUE_PROVIDER_MEMORY = "memory";
    public static final String VALUE_PROVIDER_APOLLO = "apollo";
    public static final String VALUE_PROVIDER_NACOS = "nacos";
    public static final String VALUE_PROVIDER_ZOOKEEPER = "zookeeper";

    @Value("${ds.pr:nacos}")
    private String provider = VALUE_PROVIDER_NACOS;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
