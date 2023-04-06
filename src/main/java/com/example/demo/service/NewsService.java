package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.News;
import com.example.demo.repository.NewsRepository;

@Service
public class NewsService {

    @Autowired
    private NewsRepository repository;

    public News save(News news) {
        return repository.save(news);
    }

    public List<News> getAll() {
        return repository.findAll();
    }

}