package com.arm.seller.controllers;

import com.arm.seller.entities.User;
import com.arm.seller.service.CustomUtilService;
import com.arm.seller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.synchronoss.cloud.nio.multipart.Multipart;

import java.util.stream.Stream;

@Controller
public class ApplicationController {

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String home(Model model,@RequestParam(value = "name", required = false) String name){
        if(name==null || name.trim().equals("")){
            model.addAttribute("known", false);
        }
        else{
            model.addAttribute("known", true);
            model.addAttribute("name",name);
        }
        return "home";
    }

    @GetMapping("/addNumbers")
    public String add(@RequestParam("val") String val, Model model){
        int sum = 0;
        try{
            sum = Stream.of(val.split(","))
                    .map(Integer::parseInt)
                    .reduce(0,(x,y)-> x+y);
        }
        catch(Exception ex){
            StringBuffer errorMessage= new StringBuffer();
            errorMessage.append(ex.getMessage());
            Stream.of(ex.getStackTrace())
                    .forEach(err -> errorMessage.append(err.toString()+"\n"));
            model.addAttribute("errorMessage", errorMessage);
            return "../static/error/500";
        }
        model.addAttribute("sum",sum);
        return "add";
    }

    @GetMapping("/login")
    public String loginPage(Model model, String error){
        model.addAttribute("userLogin", new User());
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/registerUser")
    public String registerUser(Model model){
        model.addAttribute("userRegister", new User());
        return "registerUser";
    }

    @PostMapping(value = "/processRegisterForm", consumes = {"application/json","application/x-www-form-urlencoded"})
    public String processUserRegistration(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.createNewUser(user);
        return "redirect:/login";
    }
}
