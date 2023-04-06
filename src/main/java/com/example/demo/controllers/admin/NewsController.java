package com.example.demo.controllers.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.News;
import com.example.demo.service.NewsService;


@Controller("AdminNewsController")
@RequestMapping("/admin/news/")
public class NewsController {

    @Autowired
    private NewsService service;
    
    @GetMapping("")
    public String index(Model model) {
        List<News> newsList = service.getAll();
        model.addAttribute("newsList", newsList);

        return "admin/news/index";
    }
    
    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        News news = service.getById(id);
        model.addAttribute("news", news);

        return "admin/news/edit";
    }
    
    @PostMapping("update/{id}")
    public String update(@PathVariable("id") Long id, News form, Model model) {
        service.update(id, form);
        return "redirect:/admin/news/";
    }
    
}
