package com.example.quiz3.web.model.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class userDTO {

    private int id;
    private String email;
    private String password;
    private String username;



}
