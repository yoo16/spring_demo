package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService service;

    @GetMapping("/article/")
    public ModelAndView index(ModelAndView model) {
        List<Article> articles = service.getAll();
        model.addObject("articles", articles);
        model.setViewName("article/index");
        return model;
    }

    @GetMapping("/article/{id}")
    public ModelAndView detail(@PathVariable("id") Long id, ModelAndView model) {
        Article article = service.getById(id);
        model.addObject("article", article);
        model.setViewName("article/index");
        return model;
    }
    
    @GetMapping("/article/search")
    public String search(@RequestParam("keyword") String keyword) {
        return "Keyword is " + keyword;
    }

}
