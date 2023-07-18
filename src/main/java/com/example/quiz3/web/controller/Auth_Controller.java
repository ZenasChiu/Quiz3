package com.example.quiz3.web.controller;


import com.example.quiz3.repository.*;
import com.example.quiz3.services.System_Auth_Services;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


import java.sql.*;
import java.util.*;


@RestController
@RequestMapping("/api")
public class Auth_Controller {
    private System_Auth_Services system_auth_services;

    public Auth_Controller(System_Auth_Services system_auth_services){
        this.system_auth_services = system_auth_services;
    }



    //@Autowired
    //System_Auth_Services system_auth_services;


    @GetMapping
    public String noResponse(){
        return "Connected";
    }


    @PostMapping("/auth/signup")
    public  ResponseEntity<Map<String, Object>> createUsers(@RequestBody Map<String, Object> JSON) throws SQLException {
        String Text = system_auth_services.createUsers(JSON);
        Map<String, String> Header = new HashMap<>();
        Header.put("message",Text);
        System.out.println(Text);
        return new ResponseEntity(Header,HttpStatus.OK);
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<Map<String, Object>> signInUsers(@RequestBody Map<String, Object> JSON , HttpServletResponse response) throws Exception{

        Cookie cookie = new Cookie( "username" , JSON.get("username").toString());
        Map<String,Object> Header = new HashMap<>();

        if(system_auth_services.loginValidation(JSON)){
            cookie.setMaxAge(7*24*60*60);
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            // 將Cookie 物件加入Response 中
            response.addCookie(cookie);
            Header = system_auth_services.getUserDetails(JSON);
        }
        else{
            Header.put("message","Sorry invalid username or password!");
        }
        // 設置過期時間，若無設置時間，其生命週期將持續到Session 過期為止

        return new ResponseEntity(Header,HttpStatus.OK);
    }

    @PostMapping("/auth/signout")
    public ResponseEntity removeUsers(@RequestBody Map<String, Object> JSON , HttpServletResponse response) throws Exception{

        return new ResponseEntity("Null",HttpStatus.BAD_REQUEST);
    }

}
