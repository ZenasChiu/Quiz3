package com.example.quiz3.web.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping({"/api/test"})
public class Test_Controller {
    /*private System_Test_Services system_test_services;
    public test_Controller(System_Test_Services system_test_services){
        this.system_test_services = system_test_services;
    }*/
    @GetMapping({"/{string}"})
    public ResponseEntity<String> noResponse(@PathVariable("string") String text){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(text, responseHeaders, HttpStatus.BAD_REQUEST);

    }

    @GetMapping({"/all"})
    public ResponseEntity getPublicResource(){
        return new ResponseEntity("Null",HttpStatus.BAD_REQUEST);
    }

    @GetMapping({"/user"})
    public ResponseEntity getRole_userResource(){
        return new ResponseEntity("Null",HttpStatus.BAD_REQUEST);
    }

    @GetMapping({"/mod"})
    public ResponseEntity getRole_Moderator_Resource(){
        return new ResponseEntity("Null",HttpStatus.BAD_REQUEST);
    }

    @GetMapping({"/admin"})
    public ResponseEntity getRole_Admin_Resource(){
        return new ResponseEntity("Null",HttpStatus.BAD_REQUEST);
    }

}