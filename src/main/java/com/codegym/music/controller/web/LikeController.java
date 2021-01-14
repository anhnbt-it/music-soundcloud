package com.codegym.music.controller.web;


import com.codegym.music.model.Song;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("like")
public class LikeController {
    @GetMapping
    public String like(@RequestParam("id") Long id){

        return "hoang";

    }
}
