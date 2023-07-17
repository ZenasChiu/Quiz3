package com.example.quiz3.web.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping({"/api/auth"})
public class Auth_Controller {
    /*private System_Auth_Services system_auth_services;
    public auth_Controller(System_Auth_Services system_auth_services){
        this.system_auth_services = system_auth_services;
    }*/

    //@Autowired
    //System_Auth_Services system_auth_services;


    @GetMapping
    public ResponseEntity noResponse(){

        return new ResponseEntity("Null",HttpStatus.BAD_REQUEST);
    }


    @PostMapping(value = "/signup")
    public  ResponseEntity<Map<String, Object>> createUsers(@RequestBody Map<String, Object> JSON)
            throws Exception {

        System.out.println(JSON);

        return new ResponseEntity(JSON,HttpStatus.OK);
    }

    @PostMapping({"/signin"})
    public ResponseEntity<Map<String, Object>> signInUsers(@RequestBody Map<String, Object> JSON , HttpServletResponse response) throws Exception{
        Cookie cookie = new Cookie( JSON.get("username").toString() , "kaijun");
        // 設置過期時間，若無設置時間，其生命週期將持續到Session 過期為止
        cookie.setMaxAge(7*24*60*60);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        // 將Cookie 物件加入Response 中
        response.addCookie(cookie);


        return new ResponseEntity(JSON,HttpStatus.OK);
    }

    @PostMapping({"/signout"})
    public ResponseEntity removeUsers(){
        return new ResponseEntity("Null",HttpStatus.BAD_REQUEST);
    }

}
/*
Cookie cookie = new Cookie("username", "kaijun");

	// 設置過期時間，若無設置時間，其生命週期將持續到Session 過期為止
	cookie.setMaxAge(7*24*60*60);

	// 將Cookie 物件加入Response 中
	response.addCookie(cookie);
 */