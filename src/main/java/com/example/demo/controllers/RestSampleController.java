package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestSampleController {
    @GetMapping("/hello")
    public String index(@RequestParam(name = "name", required = false) String name) {
        String message = "Hello, " + name;
        return message;
    }

    @GetMapping("/users/{userId}")
    public String getUserById(@PathVariable Long userId) {
        String message = "User id is " + userId + "!";
        return message;
    }

}
