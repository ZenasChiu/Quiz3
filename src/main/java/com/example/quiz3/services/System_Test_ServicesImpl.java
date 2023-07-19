package com.example.quiz3.services;


import com.example.quiz3.repository.*;
import com.example.quiz3.web.model.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Slf4j
@Service
public class System_Test_ServicesImpl implements System_Test_Services {

    @Autowired
    UserRepository userRepository;

    @Override
    public String getAllPublicContent() {
        return "Public Content.";
    }

    @Override
    public int accessValidation(String tempName) {//can improve
        Users dbUser = userRepository.findUserIdByName(tempName);
        List<String> user_roles = userRepository.findUser_role(dbUser.getId());
        System.out.println(tempName);
        System.out.println(tempName.toString());
        System.out.println(dbUser.getId());
        System.out.println(user_roles);
        int accessLevel = 0;
        for(int i = 0; i < user_roles.size();i++){
            System.out.println(user_roles.get(i));
            switch (user_roles.get(i)){
                case "ROLE_USER" : accessLevel = 1 ;break;
                case "ROLE_MODERATOR" : accessLevel = 2 ;break;
                case "ROLE_ADMIN" : accessLevel = 3 ;break;
            }
        }
        /*
        INSERT INTO roles(name) VALUES('ROLE_USER');
        INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
        INSERT INTO roles(name) VALUES('ROLE_ADMIN');
         */
        return accessLevel;
    }

    @Override
    public String getUserContent() {
        return "User Content.";
    }

    @Override
    public String getModeratorContent() {
        return "Moderator Board.";
    }

    @Override
    public String getAdminContent() {
        return "Admin Content.";
    }
}
