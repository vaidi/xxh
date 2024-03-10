package com.erlong.springbean.circulate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CirculateStudent {
    @Autowired
    private CirculateBean bean;

        public CirculateStudent(){
        System.out.println(" circulate student init ok");
    }

//    public CirculateStudent(CirculateBean bean){
//        this.bean = bean;
//        System.out.println(" circulate student init ok");
//    }


}
