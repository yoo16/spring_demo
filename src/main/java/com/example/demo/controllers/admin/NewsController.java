package com.example.demo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller("AdminNewsController")
public class NewsController {
    
    @GetMapping(value="index")
    public String index() {
        return "admin/news/index";
    }
    
}
