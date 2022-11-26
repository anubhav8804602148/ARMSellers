package com.arm.seller.service;

import com.arm.seller.entities.User;
import com.arm.seller.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    public String createNewUser(User user){
        userRepo.save(user);
        return "SUCCESS";
    }
}
