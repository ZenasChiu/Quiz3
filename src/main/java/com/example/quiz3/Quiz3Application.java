package com.example.quiz3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.quiz3","com.example.quiz3.web.model","com.example.quiz3.web.controller","com.example.quiz3.services","com.example.quiz3.repository"})
public class Quiz3Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Quiz3Application.class, args);
	}

}
