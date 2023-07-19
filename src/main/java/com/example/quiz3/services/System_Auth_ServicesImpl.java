package com.example.quiz3.services;

import com.example.quiz3.repository.*;
import com.example.quiz3.web.model.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.swing.*;
import java.sql.*;
import java.util.*;

@Slf4j
@Service
public class System_Auth_ServicesImpl implements System_Auth_Services {
    private Users users;

    @Autowired
    UserRepository userRepository;


    @Override
    public String createUsers(Map<String, Object> json) {
            users = new Users();
            String temp_password = json.get("password").toString();
            temp_password = Base64.getEncoder().encodeToString(temp_password.getBytes());
            users.setPassword(temp_password);
            users.setUsername(json.get("username").toString());
            users.setEmail(json.get("email").toString());
            userRepository.addUser(users);

            List<?> list = new ArrayList<>((Collection<?>)json.get("role"));

            Users dbUser = userRepository.findUserIdByName(users.getUsername());
            for(int i = 0; i < list.size(); i ++){
                System.out.println(list.get(i).toString());
                userRepository.addUser_role(list.get(i).toString(),dbUser.getId());
            }

            return "User registered successfully!";


    }
    @Override
    public
    Boolean loginValidation(Map<String, Object> json){
        Users dbUser = userRepository.findUserIdByName(json.get("username").toString());
        System.out.println(dbUser.getPassword());
        System.out.println(json.get("password"));
        if(dbUser.getPassword().equals(json.get("password"))){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Map<String, Object> getUserDetails(Map<String, Object> json) {
        Users dbUser = userRepository.findUserIdByName(json.get("username").toString());
        List<String> roles = userRepository.findUser_role(dbUser.getId());
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("id",dbUser.getId());
        result.put("username",dbUser.getUsername());
        result.put("email",dbUser.getEmail());
        result.put("roles",roles);

        return result;
    }
}
