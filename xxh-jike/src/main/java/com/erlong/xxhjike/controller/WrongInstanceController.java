package com.erlong.xxhjike.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@RestController
@RequestMapping("/wrong")
@Slf4j
public class WrongInstanceController {


    private static final ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);


    @GetMapping("/threadLocal")
    public Map wrong(@RequestParam("userId") Integer userId) {
        //设置用户信息之前先查询一次ThreadLocal中的用户信息
        String before = Thread.currentThread().getName() + ":" + currentUser.get();
        //设置用户信息到ThreadLocal
        currentUser.set(userId);
        //设置用户信息之后再查询一次ThreadLocal中的用户信息
        String after = Thread.currentThread().getName() + ":" + currentUser.get();
        //汇总输出两次查询结果
        HashMap result = new HashMap();
        result.put("before", before);
        result.put("after", after);
        return result;
    }


    //总原属数量
    private static int ITEM_COUNT = 1000;
    //线程个数
    private static int THREAD_COUNT = 10;

    @GetMapping("/concurrentHashMap")
    public String wrongConcurrentHashMap() throws InterruptedException {
        ConcurrentHashMap<String, Integer> concurrentHashMap = getHashMap(ITEM_COUNT);
        //初始900个元素
        log.info("init size:{}", concurrentHashMap.size());
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        //使用线程池并发处理逻辑
        forkJoinPool.execute(() -> {
            IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
                //查询还需要补充多少个元素
                int gap =ITEM_COUNT - concurrentHashMap.size();
                log.info("gap size:{}", gap);
                //补充元素
                concurrentHashMap.putAll(getHashMap(gap));
            });
        });
        // 等待所有任务完成
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        log.info("finish size:{}", concurrentHashMap.size());
        return "OK";
    }

    private ConcurrentHashMap<String, Integer> getHashMap(Integer timeCount) {
        return IntStream.rangeClosed(1, timeCount).boxed().collect(Collectors.toConcurrentMap(i -> UUID.randomUUID().toString()
                , Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
    }


}
