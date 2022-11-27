package com.arm.seller.controllers;

import com.arm.seller.repositories.AuthorityRepository;
import com.arm.seller.repositories.RoleRepository;
import com.arm.seller.repositories.UserRepository;
import com.arm.seller.service.CustomUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@Controller
public class RoleController {

    @Autowired
    CustomUtilService userDetailsService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/rolesManager")
    public String rolesManager(Model model){
        model.addAttribute("allRoles", roleRepository.findAll());
        model.addAttribute("allAuthorities", authorityRepository.findAll());
        model.addAttribute("allUsers", userRepository.findAll().stream().map(user -> userDetailsService.getDisplayDetails(user)).collect(Collectors.toList()));
        return "rolesManager";
    }
}
