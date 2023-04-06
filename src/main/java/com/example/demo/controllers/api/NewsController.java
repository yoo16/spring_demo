package com.example.demo.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.News;
import com.example.demo.service.NewsService;

@RestController("ApiNewsController")
@RequestMapping("/api/news")
public class NewsController {
    @Autowired
    private NewsService service;

    @GetMapping(path = "/all")
    public Iterable<News> getAll() {
        return service.getAll();
    }
}