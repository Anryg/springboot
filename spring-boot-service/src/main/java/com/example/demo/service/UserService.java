package com.example.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

/**
 * @DESC: User登录相关的一些列服务
 * @Auther: Anryg
 * @Date: 2020/10/16 11:10
 */
public interface UserService {
    /**添加User*/
    String insert(@NotNull String name, @NotNull int roleID, @NotNull String password);

    /**User登录*/
    boolean login(HttpServletRequest request);

    /**User退出登录*/
    void logout(HttpSession session);

}
