package com.arm.seller.service;

import com.arm.seller.entities.User;
import com.arm.seller.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepo;

    @Autowired
    CustomUtilService utilService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepo.findUserByUsername(username);
        if(user.getPassword().equals(password)) return new UsernamePasswordAuthenticationToken(
                username, password, utilService.getUserAuthorities(user)
        );
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
