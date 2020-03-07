package com.erlong.springbean.beandemo;

import com.google.common.base.Objects;
import lombok.Data;
import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
public class Xxh implements BeanNameAware {
    {
        System.out.println("这是一个动态方法");
    }
    static {
        System.out.println("这是一个静态方法");
    }



    @PostConstruct
    private void postConstruct(){
        System.out.println("postConstruct"+",xxh:"+xxh+",finalXxh:"+finalXxh+",userName:"+userName);
    }
    @PreDestroy
    public void destroyUser(){
        System.out.println("PreDestroy"+",xxh:"+xxh+",finalXxh:"+finalXxh+",userName:"+userName);
    }
    public Xxh(){
        System.out.println("实例化"+",xxh:"+xxh+",finalXxh:"+finalXxh+",userName:"+userName);
    }

    private void init(){
        System.out.println("当前bean初始化之前会加载这个内容"+",xxh:"+xxh+",finalXxh:"+finalXxh+",userName:"+userName);
    }
    private void destroy(){
        System.out.println("当前bean破坏的时候会这个内容"+",xxh:"+xxh+",finalXxh:"+finalXxh+",userName:"+userName);
    }
    private static  String xxh ="xxh";
    private final String finalXxh = "finalXxh";

    private String userName;

    private String mobile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Xxh)) return false;
        Xxh xxh = (Xxh) o;
        return Objects.equal(getFinalXxh(), xxh.getFinalXxh()) &&
                Objects.equal(getUserName(), xxh.getUserName()) &&
                Objects.equal(getMobile(), xxh.getMobile());
    }


    public void setUserName(String userName) {
        System.out.println("注入userName属性");
        this.userName = "新华";
    }

    public void setMobile(String mobile) {
        System.out.println("mobile");
        this.mobile = mobile;
    }

    public String getUserName() {
        return "新华";
    }

    public String getMobile() {
        return "132533";
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getFinalXxh(), getUserName(), getMobile());
    }


    @Override
    public void setBeanName(String name) {
    }
}
