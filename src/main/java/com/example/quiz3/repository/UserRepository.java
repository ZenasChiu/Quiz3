package com.example.quiz3.repository;
import com.example.quiz3.web.model.User_roles;
import com.example.quiz3.web.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public class UserRepository  {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addStaff(Users users, User_roles user_roles){
        System.out.println("EXCUTE INSERT MEMBER");
        jdbcTemplate.update("INSERT INTO users(email, password, username) "
                        + "VALUES (?,?,?)",users.getEmail(),users.getPassword(),users.getUsername());
        jdbcTemplate.update("INSERT INTO roles(user_id, roles_id) "
                + "VALUES (?,?)",users.getId(),users.getPassword(),users.getUsername());
    }



}
