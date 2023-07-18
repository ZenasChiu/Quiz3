package com.example.quiz3.web.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20)
    private int id;

    @Column(length = 20)
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


/*
use sys;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(120) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT Users UNIQUE(`email`,`username`)
  )
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  CONSTRAINT Roles PRIMARY KEY (`id`)
  )
CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL ,
  CONSTRAINT User_Roles PRIMARY KEY (`user_id`,`role_id`)
  )

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

*/