package com.erlong.springbean;

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


}
