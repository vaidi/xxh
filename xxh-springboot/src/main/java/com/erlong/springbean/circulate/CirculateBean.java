package com.erlong.springbean.circulate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CirculateBean {

    @Autowired
    private CirculateStudent circulateStudent;

    public CirculateBean(){
        System.out.println("circulate bean init ok");
    }

//    public CirculateBean(CirculateStudent student){
//        this.circulateStudent = student;
//        System.out.println("circulate bean init ok");
//    }

}
