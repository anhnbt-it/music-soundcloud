package com.codegym.music.controller.admin;

import com.codegym.music.model.Blog;
import com.codegym.music.model.Category;
import com.codegym.music.service.BlogService;
import com.codegym.music.service.CategoryService;
import com.codegym.music.storage.StorageException;
import com.codegym.music.storage.StorageService;
import com.codegym.music.validator.CustomFileValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("admin/blogs")
public class BlogController {

    Logger log = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CustomFileValidator customFileValidator;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StorageService storageService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        Iterable<Category> categories = categoryService.findAll();
        return categories;
    }

    @GetMapping
    public ModelAndView index(@RequestParam("s") Optional<String> s,
                              @RequestParam(defaultValue = "0") Integer pageNo,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "id") String sortBy) {
        Page<Blog> blogs;
        if (s.isPresent()) {
            blogs = blogService.findAllByTitleContains(s.get(), pageNo, pageSize, sortBy);
        } else {
            blogs = blogService.findAll(pageNo, pageSize, sortBy);
        }
        ModelAndView modelAndView = new ModelAndView("admin/blogs/index");
        modelAndView.addObject("blogs", blogs);
        modelAndView.addObject("txtSearch", s);
        return modelAndView;
    }

    @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<Blog> findAllByTitleContains(@RequestParam("s") Optional<String> s,
                                             @RequestParam(defaultValue = "0") Integer pageNo,
                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                             @RequestParam(defaultValue = "id") String sortBy) {
        Page<Blog> blogs;
        if (s.isPresent()) {
            blogs = blogService.findAllByTitleContains(s.get(), pageNo, pageSize, sortBy);
        } else {
            blogs = blogService.findAll(pageNo, pageSize, sortBy);
        }
        return blogs;
    }

    //    @RequestMapping("{id}")
//    public ModelAndView view(@PathVariable("id") Blog blog) {
//        return new ModelAndView("messages/show", "message", blog);
//    }
//
//    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//    public String delete(@PathVariable("id") Message message, RedirectAttributes redirect) {
//        messageRepository.delete(message);
//        redirect.addFlashAttribute("globalMessage", "Message removed successfully");
//        return "redirect:/";
//    }
//
    @GetMapping("create")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("admin/blogs/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping
    public String saveBlog(@Validated @ModelAttribute("blog") Blog blog, BindingResult result, RedirectAttributes redirect) {
        MultipartFile file = blog.getImageData();
        customFileValidator.validate(blog, result);
        if (result.hasErrors()) {
            return "admin/blogs/create";
        }
        try {
            storageService.store(file);
            log.info("ANHNBT: " + file.getOriginalFilename());
            blog.setImageURL(file.getOriginalFilename());
        } catch (StorageException e) {
            blog.setImageURL("150.png");
            log.info("ANHNBT: ", e);
        }
        blog.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
        blog.setUpdatedAt(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
        blog = blogService.save(blog);
        log.info("Create Blog: " + blog.toString());
        redirect.addFlashAttribute("message", "<div class=\"alert alert-success\">" + messageSource.getMessage("alert.created", new Object[] {blog.getTitle()}, Locale.getDefault()) + "</div>");
        return "redirect:/admin/blogs";
    }

    @PostMapping("delete")
    public String deleteById(@RequestParam("id") Long id, RedirectAttributes redirect) {
        blogService.deleteById(id);
        redirect.addFlashAttribute("message", "<div class=\"alert alert-success\">" + messageSource.getMessage("alert.deleted", new Object[] {id}, Locale.getDefault()) + "</div>");
        return "redirect:/admin/blogs";
    }

    @GetMapping("edit/{id}")
    public String findById(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            return "admin/blogs/edit";
        } else {
            redirect.addFlashAttribute("message", "<div class=\"alert alert-danger\">" + messageSource.getMessage("alert.notfound", new Object[] {id}, Locale.getDefault()) + "</div>");
            return "redirect:/admin/blogs";
        }
    }

    @PostMapping("update")
    public String updateBlog(@Validated @ModelAttribute("blog") Blog blog, BindingResult result, RedirectAttributes redirect) {

        MultipartFile file = blog.getImageData();
        if (blog.getImageURL() == null) {
            customFileValidator.validate(blog, result);
        }
        if (result.hasErrors()) {
            return "admin/blogs/create";
        }
        try {
            storageService.store(file);
            log.info("ANHNBT: " + file.getOriginalFilename());
            blog.setImageURL(file.getOriginalFilename());
        } catch (StorageException e) {
            if (blog.getImageURL() == null) {
                blog.setImageURL("150.png");
            }
            log.info("ANHNBT: ", e);
        }
        blog.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
        blog.setUpdatedAt(LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")));
        blog = blogService.save(blog);
        log.info("Create Blog: " + blog.toString());
        redirect.addFlashAttribute("message", "<div class=\"alert alert-success\">" + messageSource.getMessage("alert.updated", new Object[] {blog.getTitle()}, Locale.getDefault()) + "</div>");
        return "redirect:/admin/blogs";
    }
}
