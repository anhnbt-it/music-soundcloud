package com.codegym.music.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "web/home";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "errors/403";
    }

}
