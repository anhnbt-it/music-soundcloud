package com.codegym.music.controller.web;

import com.codegym.music.model.Singer;
import com.codegym.music.service.SingerService;
import com.codegym.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("singer")
public class HomeSingerController {
    @Autowired
    SingerService singerService;

    @Autowired
    SongService songService;

    @GetMapping("{id}")
    public String show(@PathVariable Long id, Model model) {
        Singer singers = singerService.findById(id).get();
        model.addAttribute("singer", singers);
        model.addAttribute("songs",songService.findAllBySingerId(id));
        return "web/singers/listofsongs";
    }
}
