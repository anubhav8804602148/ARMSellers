package com.auth.demo.controllers;

import com.auth.demo.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class ApplicationRestController {

    @Autowired
    NumberService numberService;

    @GetMapping("/getNaturalNumbers")
    public int[] getNaturalNumbers(){
        return new int[]{1,2,3,4,5,6,7,8,9,10};
    }

    @GetMapping("/getFactorial/{num}")
    public String getFactorial(@PathVariable("num") int num){
        return numberService.factorial(num).toString();
    }

    @PostMapping(value = "/getSquare", consumes = {"application/json"})
    public String getSquare(@RequestBody int num){
        return Integer.toString(num*num);
    }

    @GetMapping("/getSquareRoot")
    public String getSquareRoot(@RequestParam("num") int num){
        return Double.toString(Math.sqrt(num));
    }
}
