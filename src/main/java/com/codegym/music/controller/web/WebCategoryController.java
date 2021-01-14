package com.codegym.music.controller.web;

import com.codegym.music.exception.SongNotFoundException;
import com.codegym.music.model.Category;
import com.codegym.music.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("categories")
public class WebCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("web/category/index");
        modelAndView.addObject("categories", categoryService.findAll());
        return modelAndView;
    }

    @GetMapping("{id}")
    public ModelAndView show(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent()) {
            throw new SongNotFoundException(id);
        }
        ModelAndView modelAndView = new ModelAndView("web/category/show");
        modelAndView.addObject("category", category.get());
        return modelAndView;
    }

}
