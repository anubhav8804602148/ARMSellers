package com.arm.seller.configure;

import com.arm.seller.service.CustomAuthenticationProvider;
import com.arm.seller.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder){
        authenticationManagerBuilder.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
//                .requiresChannel(channel -> channel.anyRequest().requiresSecure())
                .authorizeRequests()
                .antMatchers("/home").authenticated()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/public/**").permitAll()
                .and()
                .formLogin()
                        .loginPage("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login").permitAll();

    }


}
