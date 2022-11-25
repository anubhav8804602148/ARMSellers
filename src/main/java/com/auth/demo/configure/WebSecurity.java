package com.auth.demo.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers("/addNumbers").authenticated()
                .anyRequest().permitAll();
    }
}
