package com.example.demo.entity;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "articles")
public class Article extends AbstractEntity {

    @NotBlank(message = "タイトルを入力してください")
    private String title;

    @NotBlank(message = "本文を入力してください")
    private String body;
    private String imagePath;

    @NotNull(message = "日付を入力してください")
    @Column(name = "posted_at", columnDefinition = "TIMESTAMP")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime postedAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body.replace("&nbsp", "\n");
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public String showDatetime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd");
        return postedAt.format(dtf);
    }

    public void setPostedAt(LocalDateTime postedAt) {
        this.postedAt = postedAt;
    }

}
