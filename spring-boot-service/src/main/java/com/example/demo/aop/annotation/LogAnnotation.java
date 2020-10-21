package com.example.demo.aop.annotation;

import java.lang.annotation.*;

/**
 * @DESC:
 * @Auther: Anryg
 * @Date: 2020/9/9 16:57
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    /**
     * @DESC: 提供注解描述说明
     * */
    String desc() default "";
}
