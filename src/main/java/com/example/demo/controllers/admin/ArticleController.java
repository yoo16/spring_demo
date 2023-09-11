package com.example.demo.controllers.admin;

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
import org.springframework.web.servlet.ModelAndView;
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
    public ModelAndView index(Model model, ModelAndView view) {
        // List<Article> articles = service.getAll();
        // model.addAttribute("articles", articles);
        view.setViewName("admin/article/index");
        return view;
    }

    @GetMapping("create")
    public ModelAndView create(
            @ModelAttribute("form") Article form,
            Model model,
            ModelAndView view) {
        // System.out.println("title:" + form.getTitle());
        // model.addAttribute("article", form);
        view.setViewName("admin/article/create");
        return view;
    }

    @PostMapping("add")
    public ModelAndView add(
            @Valid @ModelAttribute("form") Article form,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model,
            ModelAndView view,
            @RequestParam("file") MultipartFile filePart) {
        System.out.println("Add!!");
        if (bindingResult.hasErrors()) {
            // redirectAttributes.addFlashAttribute("form", form);
            // view.setViewName("redirect:/admin/article/create");
            model.addAttribute("article", form);
            view.setViewName("admin/article/create");
        } else {
            Article article = service.create(form);
            service.uploadImage(article, filePart);
            view.setViewName("redirect:/admin/article/");
        }
        return view;
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(
            @PathVariable("id") Long id,
            Model model,
            ModelAndView view) {
        System.out.println(id);
        Article article = service.getById(id);
        model.addAttribute("article", article);
        view.setViewName("admin/article/edit");
        return view;
    }

    @PostMapping("update/{id}")
    public ModelAndView update(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute Article form,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            Model model,
            ModelAndView view,
            @RequestParam("file") MultipartFile filePart) {

        model.addAttribute("id", id);
        System.out.println(form.getPostedAt());
        if (bindingResult.hasErrors()) {
            model.addAttribute("article", form);
            System.out.println("Error!!!!");
            view.setViewName("admin/article/edit");
            // redirectAttributes.addFlashAttribute("form", form);
            // view.setViewName("redirect:/admin/article/edit/" + id);
        } else {
            Article article = service.update(id, form);
            service.uploadImage(article, filePart);
            view.setViewName("redirect:/admin/article/");
        }
        return view;
    }

    @PostMapping("destroy/{id}")
    public ModelAndView destroy(@PathVariable("id") Long id, ModelAndView view) {
        service.delete(id);
        view.setViewName("redirect:/admin/article/");
        return view;
    }
}
