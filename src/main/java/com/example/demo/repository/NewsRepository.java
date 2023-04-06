package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.News;

public interface NewsRepository extends JpaRepository<News, Long> {

    Optional<News> findById(Long id);

}