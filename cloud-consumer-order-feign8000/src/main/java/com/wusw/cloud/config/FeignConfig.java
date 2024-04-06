package com.wusw.cloud.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    // 开启-重试机制
    @Bean
    public Retryer myRetryer() {
        return new Retryer.Default();
        // 初次间隔 最大间隔 最大请求次数(1+2) = 3
//        return new Retryer.Default(100, 1, 3);
    }

    // 开启-日志记录
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
