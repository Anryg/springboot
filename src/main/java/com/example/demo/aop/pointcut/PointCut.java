package com.example.demo.aop.pointcut;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @DESC:
 * @Auther: Anryg
 * @Date: 2020/9/9 16:28
 */
@Component(value = "pointCut")
@Aspect
@Slf4j
public class PointCut {

    @Pointcut("@annotation(com.example.demo.aop.annotation.LogAnnotation)")
    public void logPointCut(){
    }
}
