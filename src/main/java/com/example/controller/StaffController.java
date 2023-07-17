package com.example.controller;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.StaffModel;
import com.example.demo.services.StaffService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/spring-project-2026")
public class StaffController {

    @Autowired
    StaffModel staffModel;

    @Autowired
    StaffService staffService;

    @GetMapping("/addStaff")
    public String hello(){
        staffModel = new StaffModel();
        staffModel.setPassword("1234");
        staffModel.setEmail("email@email.com");
        staffModel.setPhone("22334455");
        staffModel.setPosition("Manager");
        staffService.addStaff(staffModel);
        return "New Staff added";


    }

    public static void queryForList(JdbcTemplate jdbcTemplate) {
               String sql = "select * from user";
       //        第三个参数可以省略
               List<User> users = jdbcTemplate.query(sql);
               System.out.println(users);
           }




}

