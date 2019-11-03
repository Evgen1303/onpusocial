//package com.hunghost.onpusocial.controller;
//
//import com.hunghost.onpusocial.service.user.UserAuthService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/")
//public class AuthController {
//    private UserAuthService userAuthService;
//
//    @Autowired
//    public AuthController(UserAuthService userAuthService) {
//        this.userAuthService = userAuthService;
//    }
//
//    @GetMapping("login/{login}")
//    public UserDetails getUser(@PathVariable String login) {
//        return userAuthService.loadUserByUsername(login);
//    }
//
//
//}
