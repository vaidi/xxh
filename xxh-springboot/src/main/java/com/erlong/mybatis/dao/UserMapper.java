package com.erlong.mybatis.dao;

import com.erlong.mybatis.entity.UserEntity;

//@Repository
//@Component
public interface UserMapper {

    UserEntity selectByUserId(Long userId);


    int updateOne(UserEntity userEntity);
}
