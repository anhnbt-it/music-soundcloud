package com.codegym.music.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;
import java.util.Optional;

@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping
    public ModelAndView index(@RequestParam("q") Optional<String> query, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("web/search/index");
        return modelAndView;
    }

}
