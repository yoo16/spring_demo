package com.example.demo.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;

@RestController("Api/ArticleController")
@RequestMapping("/api/article/")
public class ArticleController {
    @Autowired
    private ArticleService service;

    @GetMapping(path = "latest")
    public Iterable<Article> latest() {
        return service.getLatest(5);
        // return null;
    }

    @GetMapping(path = "all")
    public Iterable<Article> all() {
        Iterable<Article> results = service.getAll();
        for (Article article : results) {
            System.out.println(article.getTitle());
        }
        return results;
    }

    @GetMapping(path = "get/{id}")
    public Article get(@PathVariable("id") Long id) {
        return service.getById(id);
    }
    
    @GetMapping(path ="search")
    public Iterable<Article> search(@RequestParam(name = "keyword", required = false) String keyword) {
        Iterable<Article> results = service.search(keyword);
        return results;
    }
}