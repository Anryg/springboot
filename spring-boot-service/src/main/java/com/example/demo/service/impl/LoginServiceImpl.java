package com.example.demo.service.impl;

import com.example.demo.model.ReturnStatus;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

/**
 * @DESC:
 * @Auther: Anryg
 * @Date: 2020/10/15 17:31
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @DESC: 创建用户
     * */
    @Override
    public String insert(@NotNull String name, @NotNull int roleID, @NotNull String password){

        int code = jdbcTemplate.update("INSERT INTO user(name,role_id,password) VALUES (?,?,?)", name, roleID, password);
        if (code > 0) return ReturnStatus.OK.toString();
        else return ReturnStatus.ERROR.toString();
    }


    
}
