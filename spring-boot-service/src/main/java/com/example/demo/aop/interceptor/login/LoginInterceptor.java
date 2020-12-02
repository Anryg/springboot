package com.example.demo.aop.interceptor.login;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @DESC: 对所有请求进行用户登录验证拦截，确保所有访问都是登录之后的
 * @Auther: Anryg
 * @Date: 2020/12/2 15:13
 */
/*public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isValid = request.isRequestedSessionIdValid();*//**验证当前session是否有效,并刷新session的有效时间*//*
        return isValid;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        *//**暂时没有实现*//*
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        *//**暂时没有实现*//*
    }
}*/
