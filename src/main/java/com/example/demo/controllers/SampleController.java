package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
    
    @GetMapping("/sample")
    public String index() {
        return "/sample";
    }

}
