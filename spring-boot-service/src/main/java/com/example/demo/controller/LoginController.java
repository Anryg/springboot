package com.example.demo.controller;

import com.example.demo.aop.annotation.LogAnnotation;
import com.example.demo.model.ReturnStatus;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @DESC:
 * @Auther: Anryg
 * @Date: 2020/10/15 17:18
 */
@RestController
@RequestMapping(path = "/user")
public class LoginController {

    @Autowired
    LoginService loginService;



    /**
     * @DESC:
     * */
    @LogAnnotation(desc = "调用添加用户方法...")
    @RequestMapping(path = "/add")
    public String addUser(@RequestParam @NotNull String name, @RequestParam @NotNull int roleID, @NotNull String password){
        return loginService.insert(name,roleID,password);
    }
}
