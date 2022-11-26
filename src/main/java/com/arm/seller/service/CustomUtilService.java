package com.arm.seller.service;

import com.arm.seller.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUtilService {

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
}
