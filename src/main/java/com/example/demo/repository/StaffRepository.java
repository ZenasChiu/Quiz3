package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.StaffModel;

import java.util.List;
import java.util.Map;

@Repository
public class StaffRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addStaff(StaffModel staffModel){
        System.out.println("EXCUTE INSERT MEMBER");
        jdbcTemplate.update("INSERT INTO staffInfo(PASSWORD, EMAIL, POSITION, PHONE, CREATE_DATE) "
                        + "VALUES (?,?,?,?,NOW())",staffModel.getPassword(), staffModel.getEmail(),
                staffModel.getPosition(),staffModel.getPhone());
    }


    public List<Map<String, Object>> getAllUser() {
        String sql = "select * from staffInfo";
        return jdbcTemplate.query(sql);
    }
}