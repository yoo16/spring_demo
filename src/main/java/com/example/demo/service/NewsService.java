package com.example.demo.service;

import java.sql.Timestamp;
import java.util.Date;
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

    // TODO: not found
    public News getById(Long id) {
        return repository.findById(id).get();
    }

    public News update(Long id, News form) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        News news = getById(id);
        news.setTitle(form.getTitle());
        news.setBody(form.getBody());
        news.setUpdatedAt(now);
        repository.saveAndFlush(news);
        return news;
    }

}