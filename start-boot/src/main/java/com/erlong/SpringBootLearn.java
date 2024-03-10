package com.erlong;


import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableAsync
@MapperScan("com.erlong.dao")
//@EnableAspectJAutoProxy(exposeProxy=true)//这个用来解决aopProxy代理模式  AopContext来获取代理对象来执行方法
public class SpringBootLearn implements ApplicationListener{

    private static Logger log = LoggerFactory.getLogger(SpringBootLearn.class);

    public static void main(String[] args) {
        initFlowRules();
        SpringApplication.run(SpringBootLearn.class, args);
    }


    public static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<FlowRule>();
        //定义一条规则
        FlowRule rule1 = new FlowRule();
        //规则是针对哪个资源，这个资源名可以任意定义，不定义的时候默认是url的值
        rule1.setResource("rule01");
        // QPS控制在2以内
        rule1.setCount(2);
        // QPS限流
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule1.setLimitApp("default");
        rules.add(rule1);
        FlowRuleManager.loadRules(rules);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContextRefreshedEvent){
            log.info("项目启动完成");
        }else if(event instanceof ContextClosedEvent){
            log.info("项目关闭");
        }



    }
}
