package com.codegym.music.controller.admin;


import com.codegym.music.model.Singer;
import com.codegym.music.service.SingerService;
import com.codegym.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("admin/singers")
public class SingerController {

    @Autowired
    private SongService songService;

    @Autowired
    private SingerService singerService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    Environment env;

    @GetMapping("create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("admin/singer/create");
        modelAndView.addObject("singer", new Singer());
        return modelAndView;
    }

    @PostMapping("create")
    public ModelAndView saveSinger(@ModelAttribute("singer") Singer singer) {
        singerService.save(singer);
        ModelAndView modelAndView = new ModelAndView("admin/singer/create");
        modelAndView.addObject("singer", new Singer());
        modelAndView.addObject("message", "New Singer Created Successfully");
        return modelAndView;
    }

    @GetMapping
    public ModelAndView index(@RequestParam("s") Optional<String> s, Pageable pageable) {
        Page<Singer> singers;
        if (s.isPresent()) {
//            Sort sort = Sort.by("id").descending();
            singers = singerService.findAllByNameContains(s.get(), pageable);
        } else {
            singers = singerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("admin/singer/list");
        modelAndView.addObject("singers", singers);
        modelAndView.addObject("title", messageSource.getMessage("title.singers.list", null, Locale.getDefault()));
        modelAndView.addObject("txtSearch", s);
        return modelAndView;
    }

    @GetMapping("{id}")
    public String show(@PathVariable Long id, Model model) {
        Singer singers = singerService.findById(id).get();
        model.addAttribute("singer", singers);
        model.addAttribute("songs",songService.findAllBySingerId(id));
        return "admin/singer/view";
    }

    @GetMapping("edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<Singer> singer = singerService.findById(id);
        if (singer.isPresent()) {
            model.addAttribute("singer", singer.get());
            return "admin/singer/edit";
        } else {
            redirect.addFlashAttribute("message", "Singer not found!");
            return "redirect:/admin/singers";
        }
    }

    @PostMapping("edit-singer")
    public ModelAndView updateBlog(@ModelAttribute("singer") Singer singer) {
        singerService.save(singer);
        System.out.println(singer.toString());
        ModelAndView modelAndView = new ModelAndView("/admin/singer/edit");
        modelAndView.addObject("singer", singer);
        modelAndView.addObject("message", "Singer updated sucessfully");
        return modelAndView;
    }

    @GetMapping("delete-singer/{id}")
    public String showDeleteForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        Optional<Singer> singer = singerService.findById(id);
        if (singer.isPresent()) {
            model.addAttribute("singer", singer.get());
            return "admin/singer/delete";
        } else {
            redirect.addFlashAttribute("message", "Singer not found");
            return "redirect:/admin/singers";
        }
    }

    @PostMapping("delete")
    public String deleteBlog(@ModelAttribute("singer") Singer singer, RedirectAttributes redirect) {
        singerService.deleteById(singer.getId());
        redirect.addFlashAttribute("message", "Deleted successfully.");
        return "redirect:/admin/singers";
    }
}
