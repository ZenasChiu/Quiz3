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
    public ResponseEntity<Map<String, Object>> signInUsers(@RequestBody Map<String, Object> JSON ,
                                                           HttpServletResponse response) throws Exception{
        Map<String,Object> Header = new HashMap<>();
        String temp_password = JSON.get("password").toString();
        JSON.put("password", Base64.getEncoder().encodeToString(temp_password.getBytes()));

        if(system_auth_services.loginValidation(JSON)){
            Cookie cookie = new Cookie( JSON.get("username").toString(),JSON.get("password").toString());
            cookie.setPath("/api");
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            Header = system_auth_services.getUserDetails(JSON);
        }
        else{
            Header.put("message","Sorry invalid username or password!");
        }

        return new ResponseEntity(Header,HttpStatus.OK);
    }

    @PostMapping("/auth/signout")
    public ResponseEntity<String> removeUsers(HttpServletResponse response ,HttpServletRequest request) throws Exception{
        String header ="You are sign out";
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            header = "Please sign in";
        }
        else{
            for(Cookie c : cookies){
                c.setValue("");
                c.setPath("/");
                c.setMaxAge(-1);
                c.setHttpOnly(false);
                response.addCookie(c);
            }
        }

        return new ResponseEntity(header ,HttpStatus.OK);
    }

    @GetMapping({"/test/all"})
    public ResponseEntity<String> getPublicResource(){
        String header = system_test_services.getAllPublicContent();
        return new ResponseEntity(header,HttpStatus.OK);
    }

    public Boolean accessValidaiton(Cookie[] cookies, int accessLevelRequired){
        if (cookies == null) {
            return false;
        }
        else {
            System.out.println("have cookie");
            for(Cookie c : cookies){
                String tempName = c.getName();
                String password = c.getValue();
                Map<String, Object> tempAccess = new HashMap<>();
                tempAccess.put("username",tempName);
                tempAccess.put("password",password);
                System.out.println(system_auth_services.loginValidation(tempAccess));
                if(system_auth_services.loginValidation(tempAccess)){
                    System.out.println(system_test_services.accessValidation(tempName));
                    if(system_test_services.accessValidation(tempName) >= accessLevelRequired){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @GetMapping({"/test/user"})
    public ResponseEntity<String> getRole_userResource(HttpServletRequest request){
        String header = "";
        Cookie[] cookies = request.getCookies();
        if (accessValidaiton(cookies, 1)) {
            header = system_test_services.getUserContent();
            return new ResponseEntity(header,HttpStatus.OK);
        }
        else{
            return new ResponseEntity(header,HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping({"/test/mod"})
    public ResponseEntity getRole_Moderator_Resource(HttpServletRequest request){
        String header = "";
        Cookie[] cookies = request.getCookies();
        if (accessValidaiton(cookies, 2)) {
            header = system_test_services.getModeratorContent();
            return new ResponseEntity(header,HttpStatus.OK);
        }
        else{
            return new ResponseEntity(header,HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping({"/test/admin"})
    public ResponseEntity getRole_Admin_Resource(HttpServletRequest request){
        String header = "";
        Cookie[] cookies = request.getCookies();
        if (accessValidaiton(cookies, 3)) {
            header = system_test_services.getAdminContent();
            return new ResponseEntity(header,HttpStatus.OK);
        }
        else{
            return new ResponseEntity(header,HttpStatus.UNAUTHORIZED);
        }
    }

}
