package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;

@Controller
public class ArticleController {

    private final int ARTICLE_LIMIT = 10;

    @Autowired
    private ArticleService service;

    @GetMapping("/article/")
    public ModelAndView index(
            ModelAndView model,
            Pageable pageable) {

        Page<Article> articles = service.getPage(pageable, ARTICLE_LIMIT);

        model.addObject("articles", articles);
        model.addObject("currentPage", articles.getPageable().getPageNumber() + 1);
        model.addObject("totalPage", articles.getTotalPages());

        System.out.println(articles.getPageable().getPageNumber());
        model.setViewName("article/index");
        return model;
    }

    // @GetMapping("/article/{id}")
    // public String detail(@PathVariable("id") Long id) {
    //     System.out.println("article id: " + id);
    //     return "article/sample";
    // }
    @GetMapping("/article/{id}")
    public ModelAndView detail(@PathVariable("id") Long id, ModelAndView model) {
        Article article = service.getById(id);
        model.addObject("article", article);
        model.setViewName("article/detail");
        return model;
    }

    @GetMapping("/article/search")
    public String search(@RequestParam("keyword") String keyword) {
        return "Keyword is " + keyword;
    }

}
