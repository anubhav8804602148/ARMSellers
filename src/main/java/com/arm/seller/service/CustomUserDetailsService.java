package com.arm.seller.service;

import com.arm.seller.entities.User;
import com.arm.seller.exceptions.ProductException;
import com.arm.seller.models.CustomUserDetails;
import com.arm.seller.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        if(user==null) throw new UsernameNotFoundException(username+" not found!!");
        return new CustomUserDetails(user);
    }

    public User findUserByUsername(String username){
        return userRepo.findUserByUsername(username);
    }

    public List<User> findUserByFnameOrLname(String name){
        if(name==null || "".equals(name)) return new ArrayList<>();
        return userRepo.findUserByFnameOrLname("%"+name.toUpperCase()+"%");
    }

}
