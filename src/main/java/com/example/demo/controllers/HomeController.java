package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;

@Controller
public class HomeController {
    
    @Autowired
    private ArticleService service;

    @GetMapping("/")
    public ModelAndView index(ModelAndView model) {
        String message = "Welcome、News Site!";
        model.addObject("message", message);

        List<Article> articles = service.getLatest(10);
        model.addObject("articles", articles);
        model.setViewName("home/index");
        return model;
    }

}
