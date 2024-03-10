package com.erlong.dao;

import com.erlong.dao.entity.UserEntity;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class DemoFactoryBean implements FactoryBean<UserEntity> {
    @Override
    public UserEntity getObject() throws Exception {
        return new UserEntity();
    }

    @Override
    public Class<?> getObjectType() {
        return DemoFactoryBean.class;
    }
}
