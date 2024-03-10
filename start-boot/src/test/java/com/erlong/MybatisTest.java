package com.erlong;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.erlong.dao.UserMapper;
import com.erlong.dao.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static java.lang.Thread.sleep;

@SpringBootTest(classes = SpringBootLearn.class)
public class MybatisTest {

    @Resource
    UserMapper userMapper;

    @Test
   // @Transactional(isolation = Isolation.READ_COMMITTED)
    public void testUserMapper() throws InterruptedException {
//        UserEntity user = new UserEntity();
//        user.setMobile("123");
//        user.setName("xxh");
//        userMapper.insert(user);
        boolean ok = true;
        while (ok){
            UserEntity userEntity = userMapper.selectById(1);
            System.out.println("111currentName:"+Thread.currentThread().getName()+"student:"+JSONObject.toJSON(userEntity));
        //    new Thread(()->testUpdate()).start();
          //  Thread.yield();
            sleep(1000);
            System.out.println("222currentName:"+Thread.currentThread().getName()+"student:"+JSONObject.toJSON(userEntity));
            userEntity.setName("99");
            UpdateWrapper<UserEntity> wrapper = Wrappers.update();
            wrapper.eq("id",userEntity.getId()).eq("mobile",userEntity.getMobile());
          //  int update = userMapper.update(userEntity, wrapper);
            System.err.println("测试"+userMapper.selectById(1));

            break;
//            if(update >0){
//               // System.out.println("id:");
//                break;
//            }
        }

    }
    private void testUpdate(){
        UserEntity userEntity = userMapper.selectById(1);
        UpdateWrapper<UserEntity> wrapper = Wrappers.update();
        wrapper.eq("id",userEntity.getId());
        userEntity.setMobile("99");
        userMapper.update(userEntity, wrapper);
        System.err.println("新启动线程后的查询:"+Thread.currentThread().getName()+"student:"+JSONObject.toJSON(userEntity));
    }

}
