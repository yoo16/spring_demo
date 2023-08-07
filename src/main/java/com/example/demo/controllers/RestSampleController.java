package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class RestSampleController {
   @GetMapping("/hello")
public String index(@RequestParam(name = "name", required = false) String name) {
     String message = "Hello, " + name;
     return message;
} 
}
