package com.example.quiz3.web.controller;

import com.example.quiz3.services.*;
import jakarta.servlet.http.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping({"/api"})
public class Test_Controller {

    System_Test_Services system_test_services;

    public Test_Controller(System_Test_Services system_test_services){
        this.system_test_services = system_test_services;
    }

    @GetMapping({"/test/{string}"})
    public ResponseEntity<String> noResponse(@PathVariable("string") String text){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>(text, responseHeaders, HttpStatus.BAD_REQUEST);

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