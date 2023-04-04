package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewsController {

    @GetMapping("/news/")
    public String index() {
        return "This is News Page";
    }

    @GetMapping("/news/{id}")
    public String detail(@PathVariable("id") Long id) {
        String message = "News is " + id;
        return message;
    }

    @GetMapping("/news/search")
    public String search(@RequestParam("keyword") String keyword) {
        return "Keyword is " + keyword;
    }

}
