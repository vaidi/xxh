package com.erlong.interdependence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class A {

    @Autowired
    private B b;

    @Resource(type = B.class)
    private B bb;


}
