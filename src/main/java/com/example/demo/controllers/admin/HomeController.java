package com.example.demo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("AdminHomeController")
@RequestMapping("/admin/")
public class HomeController {

    @GetMapping(value = "")
    public String index() {
        return "admin/index";
    }

}
