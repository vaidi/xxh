package com.erlong.springbean.beandemo;

import org.springframework.beans.factory.DisposableBean;

public class SpringBeanDemo3 implements DisposableBean {
    /**
     * Invoked by the containing {@code BeanFactory} on destruction of a bean.
     *
     * @throws Exception in case of shutdown errors. Exceptions will get logged
     *                   but not rethrown to allow other beans to release their resources as well.
     */
    @Override
    public void destroy() throws Exception {

    }
}
