package com.erlong.springbean.beandemo;

import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;

@Data
public class SpringBeanDemo implements BeanNameAware, BeanFactoryAware {
    private static final int i=0;

    private String beanName;

    private BeanFactory beanFactory;
    /**
     * 用来测试这个BeanNameAware这个接口
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
//    @Override
//    public  void setBeanFactory(BeanFactory beanFactory) throws BeansException{};


}
