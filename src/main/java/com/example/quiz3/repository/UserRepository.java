package com.example.quiz3.repository;
import com.example.quiz3.web.model.User_roles;
import com.example.quiz3.web.model.Users;
import com.example.quiz3.web.model.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.*;

import static org.springframework.data.util.TypeUtils.type;

@Repository
public class UserRepository  {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addUser(Users users){
        System.out.println("Adding User .........................");
        jdbcTemplate.update("INSERT INTO users(email, password, username) "
                        + "VALUES (?,?,?)",users.getEmail(),users.getPassword(),users.getUsername());

    }
    public void addUser_role(String role , long user_id) {
        System.out.println("Adding User_roles .....................");
        int role_id = 0;
        System.out.println(role);
        switch (role){
            case "user" : role_id = 1;break;
            case "mod"   : role_id = 2;break;
            case "admin"  : role_id = 3;break;
        }
        if(role_id == 0){}
        else{
            System.out.println(user_id);
            System.out.println(role_id);
            try{
            jdbcTemplate.update("INSERT INTO user_roles(role_id, user_id) "
                + "VALUES (?,?)",role_id,user_id);}
            catch (RuntimeException r){
                System.out.println(r);
            }
        }
    }

    public Users findUserIdByName(String username){
        String sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username}, new UsersMapper());

    }
    public List<String> findUser_role(long user_id){
        String sql = "select b.name from user_roles a right join roles b on a.role_id = b.id where a.user_id = ?";
        List<Map<String,Object>> result = jdbcTemplate.queryForList(sql, new Object[]{user_id});
        List<String> roles = new ArrayList<>();
        for(int ii =0; ii< result.size();ii++){
            roles.add(result.get(ii).get("name").toString());
        }
        return roles;

    }
}
