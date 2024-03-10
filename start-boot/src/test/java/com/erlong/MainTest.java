package com.erlong;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest(classes = SpringBootLearn.class)
public class MainTest {
    private static Logger log = LoggerFactory.getLogger(MainTest.class);

    /**
     * 映射调试上下文
     */
    @Test
    public void testTraceId(){
        MDC.put("xxh", UUID.randomUUID().toString());
        System.out.println(JSONObject.toJSON(MDC.getCopyOfContextMap()));
        log.debug("开始调用服务A，进行业务处理");
        log.info("业务处理完毕，可以释放空间了，避免内存泄露");
        //MDC put 一定要对应一个 remove
        MDC.remove("traceId");
        log.info("traceId {}", MDC.get("traceId"));
    }



}
