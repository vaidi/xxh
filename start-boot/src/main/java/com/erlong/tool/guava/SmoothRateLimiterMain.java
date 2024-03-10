package com.erlong.tool.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalTime;
import java.util.stream.IntStream;

/**
 * 令牌桶算法相关
 */
public class SmoothRateLimiterMain {

    public static void main(String[] args) {
        RateLimiter rateLimiter =RateLimiter.create(1);
        IntStream.range(0,1000).forEach(e->{
            rateLimiter.acquire();
            System.out.println("当前时间:"+ LocalTime.now().getSecond()+"e:"+e);

        });

    }

}
