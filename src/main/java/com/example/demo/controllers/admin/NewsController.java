package com.example.demo.controllers.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.News;
import com.example.demo.service.NewsService;

import jakarta.validation.Valid;

@Controller("AdminNewsController")
@RequestMapping("/admin/news/")
public class NewsController {

    Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService service;

    @GetMapping("")
    public String index(Model model) {
        List<News> newsList = service.getAll();
        model.addAttribute("newsList", newsList);
        return "admin/news/index";
    }

    @GetMapping("create")
    public String create(@ModelAttribute("form") News form, Model model) {
        model.addAttribute("news", form);
        return "admin/news/create";
    }

    @PostMapping("add")
    public String add(@Valid @ModelAttribute("form") News form, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model,
            @RequestParam("file") MultipartFile filePart) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("form", form);
            model.addAttribute("news", form);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.news",
                    bindingResult);
            return "admin/news/create";
            // return "redirect:/admin/news/create";
        } else {
            News news = service.create(form);
            service.uploadImage(news, filePart);
            return "redirect:/admin/news/";
        }
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        News news = service.getById(id);
        model.addAttribute("news", news);
        return "admin/news/edit";
    }

    @PostMapping("update/{id}")
    public String update(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute News form,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model,
            @RequestParam("file") MultipartFile filePart) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("news", form);
            return "/admin/news/edit";
            // redirectAttributes.addFlashAttribute("form", form);
            // redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.news",
            // bindingResult);
            // return "redirect:/admin/news/edit/" + id;
        } else {
            News news = service.update(id, form);
            service.uploadImage(news, filePart);
            return "redirect:/admin/news/";
        }
    }

    @PostMapping("destroy/{id}")
    public String destroy(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/admin/news/";
    }
}
