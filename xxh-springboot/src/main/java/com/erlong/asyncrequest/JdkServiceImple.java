package com.erlong.asyncrequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class JdkServiceImple implements JdkService {

    @Autowired
    private ApplicationContext context;

    private JdkService jService;

    @PostConstruct
    public void init(){
        jService = context.getBean("jdkServiceImple",JdkService.class);
    }



    @Override
    @Transactional
    public void say() {
        log.info("进入断点用来调试");
        log.info("进入说方法");
        JdkService jdkService =  ((JdkService)AopContext.currentProxy());
        jdkService.study();
        jService.study();
        this.study();
    }

    @Override
    @Transactional
    public void study() {
        log.info("进入学方法");
    }
}
