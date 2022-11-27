package com.arm.seller.service;

import com.arm.seller.entities.User;
import com.arm.seller.models.UserDisplayDetails;
import com.arm.seller.repositories.AuthorityRepository;
import com.arm.seller.repositories.RoleRepository;
import com.arm.seller.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomUtilService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthorityRepository authorityRepository;


    public Collection<? extends GrantedAuthority> getUserAuthorities(User user) {
        if(user==null) return new ArrayList<>();
        List<GrantedAuthority> authorityList = new ArrayList<>();
        user.getRoles().forEach(
                role -> authorityList
                            .addAll(
                                    role
                                        .getAuthorities()
                                        .stream()
                                        .map(auth -> auth.getAuthority())
                                        .collect(Collectors.toList()))
                            );
        return authorityList;
    }

    public UserDisplayDetails getDisplayDetails(User user){
        UserDisplayDetails userDisplayDetails = new UserDisplayDetails();
        userDisplayDetails.setEmail(user.getEmail());
        userDisplayDetails.setId(user.getId());
        userDisplayDetails.setFullName(user.getFullName());
        userDisplayDetails.setImg(user.getUserProfilePic());
        return userDisplayDetails;
    }
}
