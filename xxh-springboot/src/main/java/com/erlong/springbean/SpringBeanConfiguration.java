package com.erlong.springbean;

import com.erlong.springbean.beandemo.SpringBeanDemo;
import com.erlong.springbean.beandemo.SpringBeanDemo1;
import com.erlong.springbean.beandemo.Xxh;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration

public class SpringBeanConfiguration {

   // @Scope(value="singleton")
    //@Scope(value="prototype")
    @Bean(name = {"xxh1","xxh2"},value = {"xxh1","xxh2"},initMethod = "init",destroyMethod = "destroy")
    public Xxh getFavourite(){
        return new Xxh();
    }



    @Bean(name = {"springDemo"})
    public SpringBeanDemo getSpringBeanDemo(){return new SpringBeanDemo();}
    @Bean(name = {"springDemo1"})
    public SpringBeanDemo1 getSpringBeanDemo1(){return new SpringBeanDemo1();}

}
