package com.example.demo.entity.mysql;

import lombok.Data;

/**
 * @DESC: 定义登录用户角色：普通用户和管理员
 * @Auther: Anryg
 * @Date: 2020/10/15 16:19
 */
@Data
public class Role {
    private int id;/**自增id*/
    private String role;/**角色描述*/
}
