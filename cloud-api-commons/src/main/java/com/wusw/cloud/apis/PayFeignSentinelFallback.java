package com.wusw.cloud.apis;

import com.wusw.cloud.entities.PayDTO;
import com.wusw.cloud.util.Result;
import org.springframework.stereotype.Component;

@Component
public class PayFeignSentinelFallback implements PayFeignSentinelApi {
    @Override
    public Result<PayDTO> getPayByOrderNo(String orderNo) {
        return Result.fail("对方服务器宕机或者不可用，Fallback服务降级o(╥﹏╥)o");
    }
}
