package com.example.demo.service;

import javax.validation.constraints.NotNull;

/**
 * @DESC:
 * @Auther: Anryg
 * @Date: 2020/10/16 11:10
 */
public interface LoginService {
    String insert(@NotNull String name, @NotNull int roleID, @NotNull String password);
}
