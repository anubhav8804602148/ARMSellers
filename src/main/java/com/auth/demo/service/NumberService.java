package com.auth.demo.service;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Random;

@Service
public class NumberService {

    @Bean
    public Random getRandom(){
        return new Random();
    }
    public BigInteger factorial(int num){
        BigInteger curr = new BigInteger("1");
        while(num>1){
            curr = curr.multiply(new BigInteger(String.valueOf(num)));
            num--;
        }
        return curr;
    }
}
