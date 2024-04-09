package com.inexture.springsecurityexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "I am in home page";
    }

    @GetMapping("/admin/show")
    public String adminShow(){
        return "I am having admin role";
    }

    @GetMapping("/user/show")
    public String  userShow(){
        return "I am having user role";
    }

}
