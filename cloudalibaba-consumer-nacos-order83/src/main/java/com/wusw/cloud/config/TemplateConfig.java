package com.wusw.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TemplateConfig {
    @Bean
    @LoadBalanced // 这个配置支持consul的负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
