package com.example.demo.service.impl;

import com.example.demo.aop.annotation.LogAnnotation;
import com.example.demo.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @DESC:
 * @Auther: Anryg
 * @Date: 2020/9/10 21:46
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    @LogAnnotation(desc = "调用service...")
    public String service(String str) {
        return str;
    }
}
