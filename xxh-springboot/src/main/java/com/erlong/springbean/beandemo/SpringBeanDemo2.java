package com.erlong.springbean.beandemo;

import org.springframework.beans.factory.InitializingBean;

public class SpringBeanDemo2 implements InitializingBean {
    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     * 用来初始化一些东西
     */
    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
