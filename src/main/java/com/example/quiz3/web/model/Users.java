package com.example.quiz3.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;


@Component
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(length = 20)
    private long id;

    @Column(unique=true,length = 50)
    @JsonProperty("email")
    private String email;

    @Column(length = 120)
    @JsonProperty("password")
    private String password;
    @Column(unique=true,length = 20)
    @JsonProperty("username")
    private String username;


    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id){this.id = id;}

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
