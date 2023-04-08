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

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;

import jakarta.validation.Valid;

@Controller("AdminArticleController")
@RequestMapping("/admin/article/")
public class ArticleController {

    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService service;

    @GetMapping("")
    public String index(Model model) {
        List<Article> articles = service.getAll();
        model.addAttribute("articles", articles);
        return "admin/article/index";
    }

    @GetMapping("create")
    public String create(@ModelAttribute("form") Article form, Model model) {
        model.addAttribute("article", form);
        return "admin/article/create";
    }

    @PostMapping("add")
    public String add(@Valid @ModelAttribute("form") Article form, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model,
            @RequestParam("file") MultipartFile filePart) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("form", form);
            model.addAttribute("article", form);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.article",
                    bindingResult);
            return "admin/article/create";
            // return "redirect:/admin/article/create";
        } else {
            Article article = service.create(form);
            service.uploadImage(article, filePart);
            return "redirect:/admin/article/";
        }
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Article article = service.getById(id);
        model.addAttribute("article", article);
        return "admin/article/edit";
    }

    @PostMapping("update/{id}")
    public String update(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute Article form,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model,
            @RequestParam("file") MultipartFile filePart) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("article", form);
            return "/admin/article/edit";
            // redirectAttributes.addFlashAttribute("form", form);
            // redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.article",
            // bindingResult);
            // return "redirect:/admin/article/edit/" + id;
        } else {
            Article article = service.update(id, form);
            service.uploadImage(article, filePart);
            return "redirect:/admin/article/";
        }
    }

    @PostMapping("destroy/{id}")
    public String destroy(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/admin/article/";
    }
}
