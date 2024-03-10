package com.erlong.controller;

import com.alibaba.csp.sentinel.SphO;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/getUser")
    @SentinelResource(value = "rule01",blockHandler = "blockHandlerDemo",fallback = "fallBackDemo")
    public String userName(){
        if(SphO.entry("rule01")){
            try {
                return "你好吗？亲爱的";
            } finally {
                SphO.exit();
            }
        }else{
            return "别问了，烦不烦啊";
        }
    }

    /**
     * 订单查询接口抛出限流或降级时的处理逻辑
     *
     * 注意: 方法参数、返回值要与原函数保持一致
     * @return
     */
    public String blockHandlerDemo(String name, BlockException e) {
       // e.printStackTrace();
        return "被限流了， " + name;
    }

    /**
     * 订单查询接口运行时抛出的异常提供fallback处理
     *
     * 注意: 方法参数、返回值要与原函数保持一致
     * @return
     */
    public String fallBackDemo(String name, Throwable e) {
        return "被熔断了: " + name;
    }

}
