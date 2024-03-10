package com.erlong.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() throws IOException {


        // 默认连接地址 127.0.0.1:6379
//        RedissonClient redisson = Redisson.create();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://121.40.109.63:6379");
        return Redisson.create(config);
    }

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redissonClient = Redisson.create(config);
        RLock lock = redissonClient.getLock("redis://127.0.0.1:6379");
        lock.tryLock(2,3,TimeUnit.SECONDS);
        lock.lock(3,TimeUnit.SECONDS);
        lock.lock();
        lock.unlock();
    }


    public void testRedisReadWriteLock() throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://121.40.109.63:6379");
        RedissonClient redissonClient = Redisson.create(config);

        String orderId = UUID.randomUUID().toString();
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(orderId);
        new Thread(()->{
            RLock rLock = readWriteLock.writeLock();
            try {
                rLock.lock();
                rLock.tryLock(1,100,TimeUnit.SECONDS);
                System.err.println("进入了写锁，当前线程:"+Thread.currentThread().getName());
                Thread.sleep(12000);
            }catch (Exception e){

            }finally {
                System.err.println("写锁释放，当前线程:"+Thread.currentThread().getName());
                rLock.unlock();
            }
        }).start();
        Thread.sleep(1000);

        IntStream.range(1,10).forEach(e->{
            new Thread(()->{
                RLock rLock = readWriteLock.readLock();
                try {
                    System.err.println("等待写锁释放:"+Thread.currentThread().getName());
                    rLock.tryLock(100,2222, TimeUnit.SECONDS);
                    System.err.println("进入了读锁，当前线程:"+Thread.currentThread().getName());
                    Thread.sleep(1000);
                    System.err.println("进入了读锁，休眠结束，当前线程:"+Thread.currentThread().getName());
                }catch (Exception exc){

                }finally {
                    boolean heldByCurrentThread = rLock.isHeldByCurrentThread();
                    if(heldByCurrentThread){
                        rLock.unlock();
                    }
                }
            },"名称："+e).start();
        });
        Thread.sleep(12000);



    }
}
