package com.example.quiz3.services;

import com.example.quiz3.web.model.*;

import java.sql.*;
import java.util.*;


public interface System_Auth_Services {
    String createUsers(Map<String, Object> json) throws SQLException;


    Boolean loginValidation(Map<String, Object> json);

    Map<String, Object> getUserDetails(Map<String, Object> json);
}
