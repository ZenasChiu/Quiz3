package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.StaffModel;
import com.example.demo.repository.StaffRepository;

@Service
public class StaffService{

    @Autowired
    StaffRepository staffRepository;
    public void addStaff(StaffModel staffModel){
        staffRepository.addStaff(staffModel);
    }

    public List<> getAllUser(){
        staffRepository.getAllUser();
    }

}