package com.example.demo.service.impl;

import com.example.demo.model.ReturnStatus;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @DESC:
 * @Auther: Anryg
 * @Date: 2020/10/15 17:31
 */
@Service
public class UserServiceImpl implements UserService {

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

    @Override
    public boolean login(HttpServletRequest request) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from user where `name` = ? and `password` = ?", userName, password);/**查看该用户是否存在*/
        if (result.size() > 0) {
            request.getSession().setMaxInactiveInterval(600);/**一定要拿到session，交给spring保存到Redis，且过期时间只在这里设置才生效*/
            return true;
        }
        else return false;
    }

    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }


}
