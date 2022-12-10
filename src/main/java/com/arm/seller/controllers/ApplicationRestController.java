package com.arm.seller.controllers;

import com.arm.seller.entities.Authority;
import com.arm.seller.entities.Role;
import com.arm.seller.entities.User;
import com.arm.seller.repositories.AuthorityRepository;
import com.arm.seller.repositories.RoleRepository;
import com.arm.seller.repositories.UserRepository;
import com.arm.seller.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;

@CrossOrigin( origins="*",maxAge =3600)
@RestController
public class ApplicationRestController {

    @Autowired
    NumberService numberService;

    @Autowired
    AuthorityRepository authorityRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    

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
    
    

    @GetMapping(value = "/createDefaultRolesAndUser")
    public String createDefaultRolesAndUser(){
        try{
            User adminUser = new User();
            Role adminRole = new Role();
            Authority adminRead = new Authority();
            Authority adminWrite = new Authority();
            Authority adminCreate = new Authority();
            Authority adminDelete = new Authority();

            adminRead.setAuthority(new SimpleGrantedAuthority("ADMIN_READ"));
            adminWrite.setAuthority(new SimpleGrantedAuthority("ADMIN_WRITE"));
            adminCreate.setAuthority(new SimpleGrantedAuthority("ADMIN_CREATE"));
            adminDelete.setAuthority(new SimpleGrantedAuthority("ADMIN_DELETE"));
            adminRole.setAuthorities(Arrays.asList(adminRead, adminCreate, adminDelete, adminWrite));
            adminRole.setName("ADMIN_ROLE");

            adminUser.setRoles(Collections.singletonList(adminRole));
            adminUser.setFname("ANUBHAV");
            adminUser.setLname("SHARMA");
            adminUser.setEmail("anubhav.sharma@armseller.com");
            adminUser.setExpired(false);
            adminUser.setLocked(false);
            adminUser.setPassword("$2a$10$VPya5RFOPv1YzrXBPpz6kOza22xOvP9bn91CQwzNGIktSgTe66cae");

            authorityRepository.save(adminRead);
            authorityRepository.save(adminWrite);
            authorityRepository.save(adminCreate);
            authorityRepository.save(adminDelete);
            roleRepository.save(adminRole);
            userRepository.save(adminUser);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return "SUCCESS";
    }
}
