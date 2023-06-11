package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public ModelAndView index(ModelAndView model) {
        String message = "Welcome、News Site!";
        model.addObject("message", message);
        model.setViewName("home/index");
        return model;
    }

}
