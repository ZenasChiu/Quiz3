package com.example.quiz3.web.model.mapper;

import com.example.quiz3.web.model.*;
import org.apache.catalina.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.*;

import java.sql.*;

public class UsersMapper implements RowMapper<Users> {
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        Users users = new Users();
               users.setEmail( rs.getString("email"));
               users.setPassword( rs.getString("password"));
               users.setUsername( rs.getString("username"));
               users.setId(rs.getInt("id"));
        return users;
    }

}
