package com.auth.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@Controller
public class ApplicationController {
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
}
