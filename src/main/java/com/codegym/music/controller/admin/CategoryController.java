package com.codegym.music.controller.admin;

import com.codegym.music.model.Blog;
import com.codegym.music.model.Category;
import com.codegym.music.service.CategoryService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }


    @GetMapping
    public ModelAndView index(@RequestParam("s") Optional<String> s,
                              @RequestParam(defaultValue = "0") Integer pageNo,
                              @RequestParam(defaultValue = "5") Integer pageSize,
                              @RequestParam(defaultValue = "id") String sortBy) {
        Page<Category> categories;
        if (s.isPresent()) {
//            Sort sort = Sort.by("id").descending();
            categories = categoryService.findAllByNameContains(s.get(), pageNo, pageSize, sortBy);
        } else {
            categories = categoryService.findAll(pageNo, pageSize, sortBy);
        }
        ModelAndView modelAndView = new ModelAndView("admin/categories/index");
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("txtSearch", s);
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("admin/categories/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("create")
    public String create(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "admin/categories/create";
        }
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        categoryService.save(category);
        redirect.addFlashAttribute("message", "Created category successfully");
        return "redirect:/admin/categories";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        if (id == null) {
            redirect.addFlashAttribute("Category Is NULL.");
            return "errors/404";
        }

        Optional<Category> category = categoryService.findById(id);

        if (!category.isPresent()) {
            redirect.addFlashAttribute("Category with ID \" + id + \" not found.");
            return "errors/404";
        }
        model.addAttribute("category", category.get());
        return "admin/categories/edit";
    }

    private String notFound(RedirectAttributes redirect) {
        return "errors/404";
    }

    @PostMapping("edit")
    public String edit(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "admin/categories/edit";
        }
        category.setUpdatedAt(LocalDateTime.now());
        categoryService.save(category);
        redirect.addFlashAttribute("message", "Updated category successfully");
        return "redirect:/admin/categories";
    }

    @PostMapping("delete")
    public String delete(@RequestParam("id") Long id, RedirectAttributes redirect) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            categoryService.deleteById(id);
            redirect.addFlashAttribute("message", "Successfully deleted a category");
        } else {
            redirect.addFlashAttribute("message", "Category with ID " + id + " not found.");
        }
        return "redirect:/admin/categories";
    }
}
