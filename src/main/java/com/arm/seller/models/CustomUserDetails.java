package com.arm.seller.models;

import com.arm.seller.entities.User;
import com.arm.seller.service.CustomUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private User user;
    CustomUtilService customUtilService = new CustomUtilService();

    public CustomUserDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return customUtilService.getUserAuthorities(user);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !user.isExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !user.isExpired();
    }

    @Override
    public boolean isEnabled() {
        return !user.isLocked();
    }
}
