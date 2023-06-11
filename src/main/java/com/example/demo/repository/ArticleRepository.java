package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitle(String title);
}