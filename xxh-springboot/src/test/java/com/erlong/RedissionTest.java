package com.erlong;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootLearn.class)
public class RedissionTest {

    @Autowired
    RedissonClient redissonClient;


    @Test
    public void testReadWrite(){



    }



}
