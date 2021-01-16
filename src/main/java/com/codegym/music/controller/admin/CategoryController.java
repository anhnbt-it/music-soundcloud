package com.codegym.music.controller.admin;

import com.codegym.music.model.Category;
import com.codegym.music.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }


    @GetMapping
    public ModelAndView index(@RequestParam("q") Optional<String> query, Pageable pageable) {
        Page<Category> categories;
//        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, sortBy));
        if (query.isPresent()) {
            categories = categoryService.findAllByNameContains(query.get(), pageable);
        } else {
            categories = categoryService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("admin/categories/index");
        modelAndView.addObject("title", messageSource.getMessage("title.categories.index", null, Locale.getDefault()));
        modelAndView.addObject("query", query);
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView showCategoryForm() {
        ModelAndView modelAndView = new ModelAndView("admin/categories/create");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("title", messageSource.getMessage("title.categories.add", null, Locale.getDefault()));
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

        redirect.addFlashAttribute("message", "<div class=\"alert alert-success\">" + messageSource.getMessage("alert.created", new Object[] {category.getName()}, Locale.getDefault()) + "</div>");
        return "redirect:/admin/categories";
    }

    @GetMapping("edit/{id}")
    public String showCategoryForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        if (id == null) {
            redirect.addFlashAttribute("message", "<div class=\"alert alert-danger\">" + messageSource.getMessage("alert.null", null, Locale.getDefault()) + "</div>");
            return "redirect:/admin/categories";
        }

        Optional<Category> category = categoryService.findById(id);

        if (!category.isPresent()) {
            redirect.addFlashAttribute("message", "<div class=\"alert alert-danger\">" + messageSource.getMessage("alert.notfound", new Object[]{id}, Locale.getDefault()) + "</div>");
            return "redirect:/admin/categories";
        }
        model.addAttribute("category", category.get());
        model.addAttribute("title", messageSource.getMessage("title.categories.edit", new Object[]{category.get().getName()}, Locale.getDefault()));
        return "admin/categories/edit";
    }

    @PostMapping("edit")
    public String edit(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "admin/categories/edit";
        }
        category.setUpdatedAt(LocalDateTime.now());
        categoryService.save(category);
        redirect.addFlashAttribute("message", "<div class=\"alert alert-success\">" + messageSource.getMessage("alert.updated", new Object[] {category.getName()}, Locale.getDefault()) + "</div>");
        return "redirect:/admin/categories";
    }

    @PostMapping("delete")
    public String delete(@RequestParam("id") Long id, RedirectAttributes redirect) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isPresent()) {
            redirect.addFlashAttribute("message", "<div class=\"alert alert-success\">" + messageSource.getMessage("alert.deleted", new Object[] {category.get().getName()}, Locale.getDefault()) + "</div>");
            categoryService.deleteById(id);
        } else {
            redirect.addFlashAttribute("message", "<div class=\"alert alert-danger\">" + messageSource.getMessage("alert.notfound", new Object[]{id}, Locale.getDefault()) + "</div>");
        }
        return "redirect:/admin/categories";
    }
}
