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

import com.example.demo.entity.News;
import com.example.demo.repository.NewsRepository;

@Service
public class NewsService {

    @Autowired
    private NewsRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public News save(News news) {
        return repository.save(news);
    }

    public List<News> getAll() {
        return repository.findAll();
    }

    public News getById(Long id) {
        News news = repository.findById(id).get();
        return news;
    }

    public News create(News form) {
        News news = modelMapper.map(form, News.class);

        Timestamp now = new Timestamp(System.currentTimeMillis());
        news.setCreatedAt(now);
        repository.saveAndFlush(news);
        return news;
    }

    public News update(Long id, News form) {
        News news = modelMapper.map(getById(id), News.class);

        Timestamp now = new Timestamp(System.currentTimeMillis());
        news.setUpdatedAt(now);
        repository.saveAndFlush(news);
        return news;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void uploadImage(News news, MultipartFile filePart) {
        try {
            // TODO: config & method
            String imageDir = "./src/main/resources/static/images/news/";
            String fileName = filePart.getOriginalFilename();
            if (fileName != null) {
                String ext = fileName.split("\\.")[1];
                String imageFilePath = imageDir + news.getId() + "." + ext;
                Path filePath = Paths.get(imageFilePath);

                // TODO: config & method
                String publicImagePath = "/images/news/" + news.getId() + "." + ext;
                news.setImagePath(publicImagePath);
                update(news.getId(), news);
                Files.copy(filePart.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
        }
    }
}