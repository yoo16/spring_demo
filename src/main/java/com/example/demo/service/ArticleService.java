package com.example.demo.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public Article save(Article article) {
        return repository.save(article);
    }

    public List<Article> getAll() {
        return repository.findAll(
                Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Article getById(Long id) {
        Article article = repository.findById(id).get();
        return article;
    }

    public List<Article> getArticlesByTitle(String title) {
        return repository.findByTitle(title);
    }

    public List<Article> getLatest(int limit) {
        String sql = "SELECT e FROM Article e ORDER BY e.createdAt DESC";
        return entityManager.createQuery(sql, Article.class)
                .setMaxResults(limit)
                .getResultList();
    }

    public Page<Article> getPage(Pageable pageable) {
        Page<Article> articles = repository.findAll(pageable);
        return articles;
    }

    public Page<Article> getPage(Pageable pageable, int limit) {
        int offset = pageable.getPageNumber() - 1;
        if (offset < 0)
            offset = 0;
        pageable = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "postedAt"));
        Page<Article> articles = repository.findAll(pageable);
        return articles;
    }

    public long getCount() {
        long count = repository.count();
        return count;
    }

    public List<Article> search(String keyword) {
        String sql = "SELECT e FROM Article e WHERE e.body LIKE :keyword";
        return entityManager.createQuery(sql, Article.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }

    public Article create(Article form) {
        Article article = modelMapper.map(form, Article.class);
        repository.saveAndFlush(article);
        return article;
    }

    public Article update(Long id, Article form) {
        Article existingArticle = repository.findById(id).get();
        try {
            //TODO
            modelMapper.map(form, existingArticle);
            existingArticle = repository.save(existingArticle);
        } catch (Exception e) {
            System.out.println(e);
        }
        return existingArticle;
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
                System.out.println(article.getId());
                System.out.println(fileName);
                System.out.println(imageFilePath);

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