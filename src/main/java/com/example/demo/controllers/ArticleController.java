package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService service;

    @GetMapping("/article/")
    public String index(Model model) {
        List<Article> articles = service.getAll();
        model.addAttribute("articles", articles);
        return "article/index";
    }

    @GetMapping("/article/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Article article = service.getById(id);
        model.addAttribute("article", article);
        return "article/detail";
    }
    
    @GetMapping("/article/search")
    public String search(@RequestParam("keyword") String keyword) {
        return "Keyword is " + keyword;
    }

}
