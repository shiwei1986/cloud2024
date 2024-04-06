package com.wusw.cloud.mygateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {
    private static final String BEGIN_VISIT_TIME = "begin_visit_time";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("custom global filter");
        // 1 先记录下访问接口的开始时间
        exchange.getAttributes().put(BEGIN_VISIT_TIME, System.currentTimeMillis());
        // 2 返回统计的各个结果，可以保存数据库，然后进行排序，便可以获取访问接口的前三名等业务统计
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long begin = exchange.getAttribute(BEGIN_VISIT_TIME);
            if (begin != null){
                log.info("访问接口主机："+exchange.getRequest().getURI().getHost());
                log.info("访问接口端口："+exchange.getRequest().getURI().getPort());
                log.info("访问接口URL："+exchange.getRequest().getURI().getPath());
                log.info("访问接口URL后面的参数："+exchange.getRequest().getURI().getRawQuery());
                log.info("访问接口主机："+exchange.getRequest().getURI().getHost());
                log.info("访问接口时长："+(System.currentTimeMillis()-begin+"毫秒"));
                log.info("========================分割线========================");
                System.out.println();
            }
        }));
    }

    /**
     * 数值越小优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
