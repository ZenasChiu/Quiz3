package com.example.quiz3.web.controller;

import com.example.quiz3.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/auth"})
public class auth_Controller {
    private System_Auth_Services system_auth_services;
    public auth_Controller(System_Auth_Services system_auth_services){
        this.system_auth_services = system_auth_services;
    }
    @GetMapping
    public ResponseEntity noResponse(){
        return new ResponseEntity("Null",HttpStatus.BAD_REQUEST);
    }

    @PostMapping({"/signup"})
    public ResponseEntity createUsers(){}

    @PostMapping({"/signout"})
    public ResponseEntity removeUsers(){}




}