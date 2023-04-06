package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("UserController")
@RequestMapping("/user")
public class UserController {
    
    @GetMapping("/")
    public String index() {
        return "user/index";
    }
    
}
