package com.example.quiz3.web.controller;

import com.example.quiz3.services.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/test"})
public class test_Controller {
    private System_Test_Services system_test_services;
    public test_Controller(System_Test_Services system_test_services){
        this.system_test_services = system_test_services;
    }
    @GetMapping
    public ResponseEntity noResponse(){
        return new ResponseEntity("Null",HttpStatus.BAD_REQUEST);
    }

    @GetMapping({"/all"})
    public ResponseEntity getPublicResource(){}

    @GetMapping({"/user"})
    public ResponseEntity getRole_userResource(){}

    @GetMapping({"/mod"})
    public ResponseEntity getRole_Moderator_Resource(){}

    @GetMapping({"/admin"})
    public ResponseEntity getRole_Admin_Resource(){}
}