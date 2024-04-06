package com.wusw.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wusw.cloud.entities.PayDTO;
import com.wusw.cloud.enumerate.ReturnCodeEnum;
import com.wusw.cloud.util.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
public class PayAlibabaController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/pay/nacos/{id}")
    public String getPayInfo(@PathVariable("id") Integer id) {
        return "nacos registry serverPost: " + serverPort + ", id: " + id;
    }

    // openfeign和sentinel进行服务降级和流量监控的整合处理case
    @GetMapping("/pay/nacos/get/{orderNo}")
    @SentinelResource(value = "getPayByOrderNo", blockHandler = "handlerBlockHandler")
    public Result<PayDTO> getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
        // 模拟查询
        PayDTO payDTO = new PayDTO(1024, orderNo, "pay" + IdUtil.simpleUUID(), 1, BigDecimal.valueOf(9.9), new Date());
        return Result.success(payDTO);
    }

    public Result<PayDTO> handlerBlockHandler(String orderNo, BlockException e) {
        return Result.fail(ReturnCodeEnum.RC500.getCode(),
                "getPayByOrderNo服务不可用，触发sentinel流控配置规则\to(╥﹏╥)o");
    }

//    // fallback服务降级纳入到Feign接口统一处理，全局一个
//    public Result<PayDTO> myFallBack(@PathVariable("orderNo") String orderNo, Throwable throwable) {
//        return Result.fail(ReturnCodeEnum.RC500.getCode(), "异常情况" + throwable.getMessage());
//    }
}
