package com.example.demo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Article save(Article article) {
        return repository.save(article);
    }

    public List<Article> getAll() {
        return repository.findAll();
    }

    public Article getById(Long id) {
        Article article = repository.findById(id).get();
        return article;
    }

    public Article create(Article form) {
        Article article = modelMapper.map(form, Article.class);

        Timestamp now = new Timestamp(System.currentTimeMillis());
        article.setCreatedAt(now);
        repository.saveAndFlush(article);
        return article;
    }

    public Article update(Long id, Article form) {
        Article article = modelMapper.map(getById(id), Article.class);

        Timestamp now = new Timestamp(System.currentTimeMillis());
        article.setUpdatedAt(now);
        repository.saveAndFlush(article);
        return article;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void uploadImage(Article article, MultipartFile filePart) {
        try {
            // TODO: config & method
            String imageDir = "./src/main/resources/static/images/article/";
            String fileName = filePart.getOriginalFilename();
            if (fileName != null) {
                String ext = fileName.split("\\.")[1];
                String imageFilePath = imageDir + article.getId() + "." + ext;
                Path filePath = Paths.get(imageFilePath);

                // TODO: config & method
                String publicImagePath = "/images/article/" + article.getId() + "." + ext;
                article.setImagePath(publicImagePath);
                update(article.getId(), article);
                Files.copy(filePart.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
        }
    }
}