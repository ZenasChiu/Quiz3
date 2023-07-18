package com.example.quiz3.web.controller;

import com.example.quiz3.services.*;
import jakarta.servlet.http.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class mainController {
    private System_Auth_Services system_auth_services;
    private System_Test_Services system_test_services;

    public mainController(System_Auth_Services system_auth_services,System_Test_Services system_test_services){
        this.system_auth_services = system_auth_services;
        this.system_test_services = system_test_services;
    }
    @GetMapping
    public String noResponse(){
        return "Connected";
    }


    @PostMapping("/auth/signup")
    public ResponseEntity<Map<String, Object>> createUsers(@RequestBody Map<String, Object> JSON) throws SQLException {
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

    @GetMapping({"/test/all"})
    public ResponseEntity<String> getPublicResource(){
        String header = system_test_services.getAllPublicContent();
        return new ResponseEntity(header,HttpStatus.OK);
    }


    @GetMapping({"/test/user"})
    public ResponseEntity getRole_userResource(HttpServletRequest request, HttpServletResponse response){
        String header = "";
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            header = "Please sign in";
        }
        else {
            for(Cookie c : cookies){
                System.out.println(c.getValue());
                String tempName = c.getValue();
                if(system_test_services.accessValidation(tempName) >= 1){
                    header = system_test_services.getUserContent();
                    return new ResponseEntity(header,HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity(header,HttpStatus.UNAUTHORIZED);
    }

    @GetMapping({"/test/mod"})
    public ResponseEntity getRole_Moderator_Resource(){

        return new ResponseEntity("Null",HttpStatus.BAD_REQUEST);
    }

    @GetMapping({"/test/admin"})
    public ResponseEntity getRole_Admin_Resource(){
        return new ResponseEntity("Null",HttpStatus.BAD_REQUEST);
    }

}
