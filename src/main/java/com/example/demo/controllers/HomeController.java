package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;

@Controller
public class HomeController {
    
    @Autowired
    private ArticleService service;

    @GetMapping("/")
    // @ResponseBody
    // public String index() {
    //     return "home/sample";
    // }
    // public String index(Model model) {
    //     String title = "My News";
    //     model.addAttribute("title", title);
    //     return "home/sample";
    // }

    public ModelAndView index(ModelAndView model) {
        String title = "My News";
        model.addObject("title", title);

        // List<Article> articles = service.getLatest(10);
        // model.addObject("articles", articles);
        model.setViewName("home/index");
        return model;
    }

}
