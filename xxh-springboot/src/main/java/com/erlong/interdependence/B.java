package com.erlong.interdependence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class B {
    @Autowired
    private A a;
}
