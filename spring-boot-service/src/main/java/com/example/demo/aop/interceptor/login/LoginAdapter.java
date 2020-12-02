package com.example.demo.aop.interceptor.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @DESC: 登录验证的拦截实现
 * @Auther: Anryg
 * @Date: 2020/12/2 15:33
 */
@EnableWebMvc /**所有请求第一步都会在这里进行登录验证拦截*/
@Configuration
public class LoginAdapter implements WebMvcConfigurer {

    @Autowired
    HandlerInterceptor loginInterceptor;

    /**
     * @DESC: 添加拦截器
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") /**需要拦截的路径*/
                .excludePathPatterns("/actuator/**","/user/**"); /**不用拦截的路径*/
    }

    /**
     * @DESC: 生成登录拦截器
     * */
    @Bean
    public HandlerInterceptor getLoginInterceptor(){
        HandlerInterceptor loginInterceptor = new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                boolean isValid = request.isRequestedSessionIdValid();/**验证当前session是否有效,会刷新session的有效时间*/
                return isValid;
            }
        };
        return loginInterceptor;
    }
}
