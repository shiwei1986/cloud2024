package com.wusw.cloud.controller;

import com.wusw.cloud.service.FlowLimitService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class FlowLimitController {
    @Resource
    private FlowLimitService flowLimitService;

    @GetMapping("/testA")
    public String testA() {
        return "into ... A ...";
    }

    @GetMapping("/testB")
    public String testB() {
        return "into ... B ...";
    }

    // ----------------------------------------------------
    /**
     * 流控-链路demo
     * c、d两个请求都访问flowLimitService.common()，阈值达到后对c限流，对d不管
     * @return
     */
    @GetMapping("/testC")
    public String testC() {
        flowLimitService.common();
        return "into ... C ...";
    }

    @GetMapping("/testD")
    public String testD() {
        flowLimitService.common();
        return "into ... D ...";
    }
    // ----------------------------------------------------

    /**
     * 流控效果 --- 排队等待
     * @return
     */
    @GetMapping("/testE")
    public String testE() {
        log.info("E: {}", System.currentTimeMillis());
        return "into ... E ...";
    }

    /**
     * 熔断规则---慢调用比例
     *
     * @return
     */
    @GetMapping("/testF")
    public String testF() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("熔断测试, 慢调用比例");
        return "--------testF 新增熔断规则-慢调用比例";
    }

    /**
     * 熔断--异常比例
     * @return
     */
    @GetMapping("/testG")
    public String testG() {
        System.out.println("----------测试：新增熔断规则-异常比例 ");
        int age = 10/0;
        return "-------testG，新增熔断规则-异常比例";
    }
    /**
     * 熔断--异常比例
     * @return
     */
    @GetMapping("/testH")
    public String testH() {
        System.out.println("----------测试：新增熔断规则-异常数 ");
        int age = 10/0;
        return "-------testG，新增熔断规则-异常数";
    }
}
