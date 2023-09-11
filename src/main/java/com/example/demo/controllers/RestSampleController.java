package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestSampleController {

    @GetMapping(value = "/hello")
    public String index() {
        return "This is Sample Controller!!";
    }

    @GetMapping(value = "/bye")
    // public String bye(@RequestParam String param) {
    public String bye(@RequestParam(name = "name", required = false) String param) {
        String message = "Bye!" + param;
        return message;
    }

    @GetMapping(value = "/users/{userId}")
    public String getUserById(@PathVariable Long userId) {
        String message = "User id is " + userId + "!";
        return message;
    }

}
