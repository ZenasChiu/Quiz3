package com.example.quiz3.web.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
public class User_roles {
    @Id
    @Column(length = 20)
    private long user_id;

    @Id
    @Column(length = 11)
    private int role_id;

    public int getRole_id() {
        return role_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
