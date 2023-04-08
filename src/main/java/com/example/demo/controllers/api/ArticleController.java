package com.example.demo.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;

@RestController("ApiArticleController")
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    private ArticleService service;

    @GetMapping(path = "/all")
    public Iterable<Article> getAll() {
        return service.getAll();
    }
}