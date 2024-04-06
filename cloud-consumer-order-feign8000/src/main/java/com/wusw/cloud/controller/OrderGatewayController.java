package com.wusw.cloud.controller;

import com.wusw.cloud.apis.PayFeignApi;
import com.wusw.cloud.entities.PayDTO;
import com.wusw.cloud.util.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign/gateway")
public class OrderGatewayController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/pay/get/{id}")
    public Result<PayDTO> getPayById(@PathVariable("id") Integer id) {
        return payFeignApi.getById4Gateway(id);
    }

    @GetMapping("/pay/getInfo")
    public Result<String> getInfo() {
        return payFeignApi.getInfo4Gateway();
    }

}
