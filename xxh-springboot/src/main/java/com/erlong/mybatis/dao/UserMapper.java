package com.erlong.mybatis.dao;

import com.erlong.mybatis.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface UserMapper {

    UserEntity selectByUserId(Long userId);


    int updateOne(UserEntity userEntity);
}
