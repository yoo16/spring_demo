package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    
    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        String message = "Hello, " + name;
        return message;
    }

}
