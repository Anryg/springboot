package com.example.demo.controller;

import com.example.demo.aop.annotation.LogAnnotation;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

/**
 * @DESC: 用户操作的Controller、包括用户注册、登录、退出登录、验证session是否过期等
 * @Auther: Anryg
 * @Date: 2020/10/15 17:18
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * @DESC:
     * */
    @LogAnnotation(desc = "调用添加用户方法...")
    @RequestMapping(path = "/add")
    public String addUser(@RequestParam @NotNull String name, @RequestParam @NotNull int roleID, @NotNull String password){
        return userService.insert(name,roleID,password);
    }

    @LogAnnotation(desc = "调用用户登录方法...")
    @RequestMapping(path = "/login")
    public boolean login(HttpServletRequest request){
        return userService.login(request);
    }

    @LogAnnotation(desc = "调用退出登录方法...")
    @RequestMapping(path = "/logout")
    public void logout(HttpSession httpSession){
        userService.logout(httpSession);
    }

}
