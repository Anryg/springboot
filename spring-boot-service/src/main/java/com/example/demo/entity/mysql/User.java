package com.example.demo.entity.mysql;

import lombok.Data;

/**
 * @DESC: 定义用户的属性
 * @Auther: Anryg
 * @Date: 2020/10/14 23:20
 */
@Data
public class User {
    private int id;/**自增主键*/
    private String name;/**用户名*/
    private String password;/**密码*/
    private int roleID;/**角色id*/
}
